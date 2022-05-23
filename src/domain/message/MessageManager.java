package domain.message;

import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Serializer;
import Model.Message;
import domain.product.ItemManager;

public class MessageManager {
    private final String id = "Team2";
    private final int dvmX = 22;
    private final int dvmY = 22;
    private final int TOTAL_DVM_COUNT = 2;
    private final String[] IP_ADDR = {"localhost", "localhost"};
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
            server = new DVMServer();
            try {
                System.out.println("Server running...");
                server.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                if (!server.msgList.isEmpty()) {
                    Message msg = server.msgList.get(server.msgList.size() - 1);
                    String msgType = msg.getMsgType();
                    int dstId = Integer.parseInt(msg.getSrcId());
                    Message.MessageDescription msgDes = msg.getMsgDescription();
                    int itemId = Integer.parseInt(msgDes.getItemCode());
                    int itemQuantity = msgDes.getItemNum();
                    if (msgType.equals("StockCheckRequest")) {
                        boolean available = itemManager.checkStock(itemId, itemQuantity);
                        if (available) {
                            //itemManager.update();
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
        //msgReceiver.start();
    }

    private void setMsgDes(Message.MessageDescription msgDes, int itemId, int itemQuantity, int dvmX, int dvmY, String authCode) {
        msgDes.setItemCode(Integer.toString(itemId));
        msgDes.setItemNum(itemQuantity);
        msgDes.setDvmXCoord(dvmX);
        msgDes.setDvmYCoord(dvmY);
        msgDes.setAuthCode(authCode);
    }

    private void setMsg(Message msg, String srcId, String dstId, String msgType, Message.MessageDescription msgDes) {
        msg.setSrcId(srcId);
        msg.setDstID(dstId);
        msg.setMsgType(msgType);
        msg.setMsgDescription(msgDes);
    }

    public void sendMsg(int dstId, Message msg) throws InterruptedException {
        System.err.println("sends msg to " + dstId + "(" + IP_ADDR[dstId - 1] + ")");
        String jsonMsg = new Serializer().message2Json(msg);
        DVMClient client = new DVMClient(IP_ADDR[dstId - 1], jsonMsg);
        client.run();
    }

    public void checkStockOfOtherVM(int itemId, int itemQuantity) throws InterruptedException {
        System.err.println("itemId, itemQuantity = " + itemId + ", " + itemQuantity);
        for (int i = 1; i <= TOTAL_DVM_COUNT; i++) {
            String dstId = "Team" + i;
            if (!dstId.equals(this.id)) {
                Message msg = new Message();
                Message.MessageDescription msgDes = new Message.MessageDescription();
                setMsgDes(msgDes, itemId, itemQuantity, this.dvmX, this.dvmY, null);
                setMsg(msg, this.id, dstId, "StockCheckRequest", msgDes);
                sendMsg(i, msg);
            }
        }
    }

    public void sendPrepaymentInfo(int itemId, int itemQuantity, int dstId, String verificationCode) throws InterruptedException {
        Message msg = new Message();
        Message.MessageDescription msgDes = new Message.MessageDescription();
        setMsgDes(msgDes, itemId, itemQuantity, this.dvmX, this.dvmY, verificationCode);
        setMsg(msg, this.id, "Team" + dstId, "PrepaymentCheck" , msgDes);
        sendMsg(dstId, msg);
    }

    public void sendStockMsg(int itemId, int itemQuantity, int dstId) throws InterruptedException {
        Message msg = new Message();
        Message.MessageDescription msgDes = new Message.MessageDescription();
        setMsgDes(msgDes, itemId, itemQuantity, this.dvmX, this.dvmY, null);
        setMsg(msg, this.id, "Team" + dstId, "StockCheckResponse", msgDes);
        sendMsg(dstId, msg);
    }

    public void sendProductMsg(int itemId, int itemQuantity, int dstId) throws InterruptedException {
        Message msg = new Message();
        Message.MessageDescription msgDes = new Message.MessageDescription();
        setMsgDes(msgDes, itemId, itemQuantity, this.dvmX, this.dvmY, null);
        setMsg(msg, this.id, "Team" + dstId, "SalesCheckResponse", msgDes);
        sendMsg(dstId, msg);
    }
}
