package domain.message;

import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;
import domain.product.ItemManager;

public class MessageManager {
    private static final String id = "Team2";
    private static final int dvmX = 22;
    private static final int dvmY = 22;
    private static final int TOTAL_DVM_COUNT = 2;
    private static final String[] IP_ADDR = {"localhost", "localhost"};
    private final MsgReceiver msgReceiver;
    private final ItemManager itemManager;
    /*
    public static class MsgSender extends Thread {
        private DVMClient client;
        @Override
        public void run() {
            try {
                Message msg = new Message();
                msg.setSrcId(id);
                client = new DVMClient("localhost", jsonMsg);
            } catch (Exception e) {

            }
        }
    }
     */
    public static class MsgReceiver extends Thread {
        private DVMServer server;
        private final ItemManager itemManager;
        MsgReceiver(ItemManager itemManager) {
            this.itemManager = itemManager;
        }
        @Override
        public void run() {
            try {
                System.out.println("Server running...");
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                if (!DVMServer.msgList.isEmpty()) {
                    Message msg = DVMServer.msgList.get(DVMServer.msgList.size() - 1);
                    String msgType = msg.getMsgType();
                    int dstId = Integer.parseInt(msg.getSrcId());
                    Message.MessageDescription msgDes = msg.getMsgDescription();
                    int itemId = Integer.parseInt(msgDes.getItemCode());
                    int itemQuantity = msgDes.getItemNum();
                    if (msgType.equals("StockCheckRequest")) {
                        boolean available = itemManager.checkStock(itemId, itemQuantity);
                        if (available) {

                        }
                    } else if (msgType.equals("StockCheckResponse")) {

                    } else if (msgType.equals("PrepaymentCheck")) {

                    } else if (msgType.equals("SalesCheckRequest")) {

                    }
                }
            }
        }
    }

    public MessageManager(ItemManager itemManager) {
        this.itemManager = itemManager;
        msgReceiver = new MsgReceiver(itemManager);
        msgReceiver.start();
    }

    private void setMsgDes(Message.MessageDescription msgDes, int itemId, int itemQuantity, String authCode) {
        msgDes.setItemCode(Integer.toString(itemId));
        msgDes.setItemNum(itemQuantity);
        msgDes.setDvmXCoord(MessageManager.dvmX);
        msgDes.setDvmYCoord(MessageManager.dvmY);
        msgDes.setAuthCode(authCode);
    }

    private Message setMsg(String dstId, int itemId, int itemQuantity, String msgType, String authCode) {
        Message msg = new Message();
        Message.MessageDescription msgDes = new Message.MessageDescription();
        setMsgDes(msgDes, itemId, itemQuantity, authCode);
        msg.setSrcId(MessageManager.id);
        msg.setDstID(dstId);
        msg.setMsgType(msgType);
        msg.setMsgDescription(msgDes);
        return msg;
    }

    public void sendMsg(int dstId, Message msg) {
        System.err.println("sends msg to " + dstId + "(" + IP_ADDR[dstId - 1] + ")");
        String jsonMsg = new Serializer().message2Json(msg);
        DVMClient client = new DVMClient(IP_ADDR[dstId - 1], jsonMsg);
        try {
            client.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkStockOfOtherVM(int itemId, int itemQuantity) {
        System.err.println("itemId, itemQuantity = " + itemId + ", " + itemQuantity);
        for (int i = 1; i <= TOTAL_DVM_COUNT; i++) {
            String dstId = "Team" + i;
            if (!dstId.equals(id)) {
                Message msg = setMsg(dstId, itemId, itemQuantity, "StockCheckRequest", null);
                sendMsg(i, msg);
            }
        }
    }

    public void sendPrepaymentInfo(int itemId, int itemQuantity, int dstId, String verificationCode) {
        Message msg = setMsg("Team" + dstId, itemId, itemQuantity, "PrepaymentCheck", verificationCode);
        sendMsg(dstId, msg);
    }

    public void sendStockMsg(int itemId, int itemQuantity, int dstId) {
        Message msg = setMsg("Team" + dstId, itemId, itemQuantity, "StockCheckResponse", null);
        sendMsg(dstId, msg);
    }

    public void sendProductMsg(int itemId, int itemQuantity, int dstId) {
        Message msg = setMsg("Team" + dstId, itemId, itemQuantity,"SalesCheckResponse", null);
        sendMsg(dstId, msg);
    }
}
