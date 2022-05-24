package domain.message;

import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;
import domain.product.ItemManager;

import java.util.ArrayDeque;
import java.util.Deque;

public class MessageManager extends Thread {
    private static final int WAIT_TIME = 500; // ms
    private static final String DVM_ID = "Team2";
    private static final int DVM_X = 22;
    private static final int DVM_Y = 22;
    private static final int TOTAL_DVM_COUNT = 2;
    private static final String[] IP_ADDR = {"localhost", "localhost"};
    private static final String NULL_AUTH_CODE = "0000000000";

    private static Deque<Message> msgQueue;
    private final OtherVM oVM;
    private final MsgReceiver msgReceiver;

    private static class OtherVM extends Thread {
        private static DVMServer server;

        public OtherVM() {
            server = new DVMServer();
        }

        @Override
        public void run() {
            try {
                System.err.println("DVMServer running...");
                server.run();
                System.err.println("DVMServer Stopped...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class MsgReceiver extends Thread {
        private final ItemManager itemManager;

        MsgReceiver(ItemManager itemManager) {
            this.itemManager = itemManager;
        }

        @Override
        public void run() {
            try {
                System.err.println("MsgReceiver running...");
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    Thread.sleep(WAIT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("msgList.size() = " + DVMServer.msgList.size());
                if (!DVMServer.msgList.isEmpty()) {
                    Message msg = DVMServer.msgList.remove(DVMServer.msgList.size() - 1);
                    String msgType = msg.getMsgType();
                    String authCode= msg.getMsgDescription().getAuthCode();
                    int[] msgInfo = decodeMsg(msg);
                    int otherId = msgInfo[0];
                    int itemId = msgInfo[1];
                    int itemQuantity = msgInfo[2];
                    System.err.println("(OtherId, itemId, itemQuantity, msgType) = " + otherId + ", " + itemId + ", " + itemQuantity + ", " + msgType);
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
        }
    }

    @Override
    public void run() {
        try {
            System.err.println("messageManager running...");
            //oVM.start();
            //msgReceiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MessageManager(ItemManager itemManager) {
        msgQueue = new ArrayDeque<>();
        oVM = new OtherVM();
        msgReceiver = new MsgReceiver(itemManager);
    }

    public void sendMsg(int dstId, Message msg) {
        System.err.println("sends msg to " + dstId + "(" + IP_ADDR[dstId] + ")");
        String jsonMsg = new Serializer().message2Json(msg);
        DVMClient client = new DVMClient(IP_ADDR[dstId], jsonMsg);
        try {
            client.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int[] checkStockOfOtherVM(int itemId, int itemQuantity) {
        System.err.println("itemId, itemQuantity = " + itemId + ", " + itemQuantity);
        for (int i = 1; i <= TOTAL_DVM_COUNT; i++) {
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

    private Message setMsg(String dstId, int itemId, int itemQuantity, String msgType, String authCode) {
        System.err.println("dstId, itemId, itemQuantity, msgType, authCode = " + dstId + ", " + itemId + ", " + itemQuantity + ", " + msgType + ", " + authCode);
        Message msg = new Message();
        Message.MessageDescription msgDes = new Message.MessageDescription();
        setMsgDes(msgDes, itemId, itemQuantity, authCode);
        msg.setSrcId(MessageManager.DVM_ID);
        msg.setDstID(dstId);
        msg.setMsgType(msgType);
        msg.setMsgDescription(msgDes);
        return msg;
    }

    private void setMsgDes(Message.MessageDescription msgDes, int itemId, int itemQuantity, String authCode) {
        String itemName = Integer.toString(itemId + 1);
        if (itemName.length() == 1) {
            itemName = "0" + itemName;
        }
        msgDes.setItemCode(itemName);
        msgDes.setItemNum(itemQuantity);
        msgDes.setDvmXCoord(MessageManager.DVM_X);
        msgDes.setDvmYCoord(MessageManager.DVM_Y);
        msgDes.setAuthCode(authCode);
    }

    private int[] decodeMsg(Message msg) {
        String srcName = msg.getSrcId();
        Message.MessageDescription msgDes = msg.getMsgDescription();
        int srcId = srcName.charAt(srcName.length() - 1) - '0'; // 0-indexed
        int itemId = Integer.parseInt(msgDes.getItemCode());
        int itemQuantity = msgDes.getItemNum();
        int srcX = msgDes.getDvmXCoord();
        int srcY = msgDes.getDvmYCoord();
        return new int[]{srcId, itemId, itemQuantity, srcX, srcY};
    }
}
