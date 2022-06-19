package domain.message;

import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import DVM_Server.messageListChangeListner;
import GsonConverter.Serializer;
import Model.Message;
import domain.app.Controller;
import domain.product.ItemManager;

import java.util.ArrayDeque;
import java.util.Deque;

public class MessageManager extends Thread {
    private static final int WAIT_TIME = 500; // ms
    private static final String DVM_ID = "Team2";
    private static final int DVM_X = 22;
    private static final int DVM_Y = 22;
    private static final int TOTAL_DVM_COUNT = 6;
    // we use 1-indexed array. so we need array length = TOTAL_DVM_COUNT + 1
    private static final String[] IP_ADDR = {"localhost", "localhost", "localhost", "", "", "", ""};
    private static final String NULL_AUTH_CODE = "0000000000";

    private static Deque<Message> msgQueue;

    private class MessageReceiver implements messageListChangeListner {
        private final ItemManager itemManager = Controller.getInstance().getItemManager();

        @Override
        public void onChanged(Change<? extends Message> change) {
            while (change.next()) {
                if (change.wasAdded()) {
                    receiveMessage(change);
                }
            }
        }

        private void receiveMessage(Change<? extends Message> change) {
            int listSize = change.getList().size();
            Message msg = change.getList().remove(listSize - 1);
            System.out.println(String.format("%s(): received msg = %s", "MessageReceiver.receiveMessage", MessageManager.toString(msg)));
            String msgType = msg.getMsgType();
            String authCode = msg.getMsgDescription().getAuthCode();
            int[] msgInfo = decodeMsg(msg);
            int otherId = msgInfo[0];
            int itemId = msgInfo[1];
            int itemQuantity = msgInfo[2];
            System.out.println(String.format("%s(): otherId = %d, itemId = %d, itemQuantity = %d, msgType = %s", "MessageReceiver.receiveMessage", otherId, itemId, itemQuantity, msgType));
            if (msgType.equals("StockCheckRequest")) {
                boolean stockAvailable = itemManager.checkStock(itemId, itemQuantity);
                if (stockAvailable) {
                    MessageManager.this.sendStockMsg(itemId, itemQuantity, otherId);
                }
            } else if (msgType.equals("StockCheckResponse")) {
                msgQueue.addLast(msg);
            } else if (msgType.equals("PrepaymentCheck")) {
                itemManager.synchronize(itemId, itemQuantity, authCode);
            } else if (msgType.equals("SalesCheckRequest")) {
                boolean productAvailable = itemManager.checkProduct(itemId);
                if (productAvailable) {
                    MessageManager.this.sendProductMsg(itemId, 1, otherId);
                }
            }
        }
    }

    public MessageManager() {
        msgQueue = new ArrayDeque<>();
        System.out.println(this.getClass() + " created.");
    }

    @Override
    public void run() {
        try {
            System.out.println("MessageManager.run()");
            DVMServer server = new DVMServer();
            server.setMessageListner(new MessageReceiver());
            System.out.println("MessageManager.run(): call server.run()");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // itemId: 0-indexed
    public int[] checkStockOfOtherVM(int itemId, int itemQuantity) {
        System.out.println(String.format("%s(): itemId = %d, itemQuantity = %d", "MessageManager.checkStockOfOtherVM", itemId, itemQuantity));
//        for (int i = 1; i <= TOTAL_DVM_COUNT; i++) { // for release with Team1~6
        for (int i = 0; i < 1; i++) { // for debug with Team0
            String dstId = "Team" + i;
            if (!dstId.equals(DVM_ID)) {
                Message msg = setMsg(dstId, itemId, itemQuantity, "StockCheckRequest", NULL_AUTH_CODE);
                sendMsg(i, msg);
            }
        }
        try {
            Thread.sleep(WAIT_TIME * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (msgQueue.isEmpty()) {
            return new int[]{-1, -1, -1, -1};
        }
        Message msg = msgQueue.pollLast();
        int[] msgInfo = decodeMsg(msg);
        int dstId = msgInfo[0];
        int dstX = msgInfo[3];
        int dstY = msgInfo[4];
        int dstDist = (DVM_X - dstX) * (DVM_X - dstX) + (DVM_Y - dstY) * (DVM_Y - dstY);
        while (!msgQueue.isEmpty()) {
            msg = msgQueue.pollLast();
            msgInfo = decodeMsg(msg);
            int otherId = msgInfo[0];
            int otherX = msgInfo[3];
            int otherY = msgInfo[4];
            int otherDist = (DVM_X - otherX) * (DVM_X - otherX) + (DVM_Y - otherY) * (DVM_Y - otherY);
            if (otherDist < dstDist || (otherDist == dstDist && otherId < dstId)) {
                dstDist = otherDist;
                dstId = otherId;
                dstX = otherX;
                dstY = otherY;
            }
        }
        return new int[]{dstId, dstX, dstY, dstDist};
    }

    public void sendMsg(int dstId, Message msg) {
        String jsonMsg = new Serializer().message2Json(msg);
        System.out.println(String.format("%s(): %s, ip: %s, json: %s", "MessageManager.sendMsg", msg, IP_ADDR[dstId], jsonMsg));
        DVMClient client = new DVMClient(IP_ADDR[dstId], jsonMsg);
        client.run();
    }

    public void sendPrepaymentInfo(int itemId, int itemQuantity, int dstId, String verificationCode) {
        Message msg = setMsg("Team" + dstId, itemId, itemQuantity, "PrepaymentCheck", verificationCode);
        sendMsg(dstId, msg);
    }

    public void sendStockMsg(int itemId, int itemQuantity, int dstId) {
        Message msg = setMsg("Team" + dstId, itemId, itemQuantity, "StockCheckResponse", NULL_AUTH_CODE);
        sendMsg(dstId, msg);
    }

    public void sendProductMsg(int itemId, int itemQuantity, int dstId) {
        Message msg = setMsg("Team" + dstId, itemId, itemQuantity, "SalesCheckResponse", NULL_AUTH_CODE);
        sendMsg(dstId, msg);
    }

    public static String toString(Message.MessageDescription msgDescription) {
        return String.format("MessageDescription(%s, %d, %d, %d, %s)",
                msgDescription.getItemCode(),
                msgDescription.getItemNum(),
                msgDescription.getDvmXCoord(),
                msgDescription.getDvmYCoord(),
                msgDescription.getAuthCode()
        );
    }

    public static String toString(Message msg) {
        return String.format("Message(%s, %s, %s, %s", msg.getSrcId(), msg.getDstID(), msg.getMsgType(), toString(msg.getMsgDescription()));
    }

    // itemId: 0-indexed
    private Message setMsg(String dstId, int itemId, int itemQuantity, String msgType, String authCode) {
        System.out.println("MessageManager.setMsg(): dstId, itemId, itemQuantity, msgType, authCode = " + dstId + ", " + itemId + ", " + itemQuantity + ", " + msgType + ", " + authCode);
        Message msg = new Message();
        Message.MessageDescription msgDes = new Message.MessageDescription();
        setMsgDes(msgDes, itemId, itemQuantity, authCode);
        msg.setSrcId(MessageManager.DVM_ID);
        msg.setDstID(dstId);
        msg.setMsgType(msgType);
        msg.setMsgDescription(msgDes);
        return msg;
    }

    // itemId: 0-indexed
    private void setMsgDes(Message.MessageDescription msgDes, int itemId, int itemQuantity, String authCode) {
        String itemCode = Integer.toString(itemId + 1);
        if (itemCode.length() == 1) {
            itemCode = "0" + itemCode;
        }
        msgDes.setItemCode(itemCode);
        msgDes.setItemNum(itemQuantity);
        msgDes.setDvmXCoord(MessageManager.DVM_X);
        msgDes.setDvmYCoord(MessageManager.DVM_Y);
        msgDes.setAuthCode(authCode);
    }

    private int[] decodeMsg(Message msg) {
        String srcName = msg.getSrcId();
        Message.MessageDescription msgDes = msg.getMsgDescription();
        int srcId = srcName.charAt(srcName.length() - 1) - '0'; // from 1-indexed to 0-indexed
        int itemId = Integer.parseInt(msgDes.getItemCode()) - 1; // from 1-indexed to 0-indexed
        int itemQuantity = msgDes.getItemNum();
        int srcX = msgDes.getDvmXCoord();
        int srcY = msgDes.getDvmYCoord();
        return new int[]{srcId, itemId, itemQuantity, srcX, srcY};
    }
}
