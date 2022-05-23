package domain.message;

import DVM_Client.DVMClient;
import DVM_Server.DVMServer;
import GsonConverter.Deserializer;
import GsonConverter.Serializer;
import Model.Message;
import domain.product.ItemManager;

public class MessageManager extends Thread {
    private static final String id = "Team2";
    private static final int dvmX = 22;
    private static final int dvmY = 22;
    private static final int TOTAL_DVM_COUNT = 2;
    private static final String[] IP_ADDR = {"localhost", "localhost"};
    private static final String NULL_AUTH_CODE = "0000000000";
    private final OtherVM oVM;
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
    public class OtherVM extends Thread {
        DVMServer server;

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

        public DVMServer getServer() {
            return this.server;
        }
    }

    public class MsgReceiver extends Thread {
        private final ItemManager itemManager;
        private final DVMServer server;

        MsgReceiver(DVMServer server, ItemManager itemManager) {
            this.itemManager = itemManager;
            this.server = server;
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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("msgList.size() = " + this.server.msgList.size());
                if (!this.server.msgList.isEmpty()) {
                    Message msg = this.server.msgList.remove(this.server.msgList.size() - 1);
                    String msgType = msg.getMsgType();
                    String otherName = msg.getSrcId();
                    int otherId = otherName.charAt(otherName.length() - 1) - '0';
                    Message.MessageDescription msgDes = msg.getMsgDescription();
                    int itemId = Integer.parseInt(msgDes.getItemCode());
                    int itemQuantity = msgDes.getItemNum();
                    System.err.println("(OtherId, itemId, itemQuantity, msgType) = " + otherId + ", " + itemId + ", " + itemQuantity + ", " + msgType);
                    if (msgType.equals("StockCheckRequest")) {
                        boolean stockAvailable = itemManager.checkStock(itemId, itemQuantity);
                        if (stockAvailable) {
                            MessageManager.this.sendStockMsg(itemId, itemQuantity, otherId);
                        }
                    } else if (msgType.equals("StockCheckResponse")) {

                    } else if (msgType.equals("PrepaymentCheck")) {

                    } else if (msgType.equals("SalesCheckRequest")) {

                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            System.err.println("msgManager running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MessageManager(ItemManager itemManager) {
        this.itemManager = itemManager;
        this.oVM = new OtherVM();
        this.oVM.start();
        System.err.println("OVM is Running");

        this.msgReceiver = new MsgReceiver(oVM.getServer(), itemManager);
        this.msgReceiver.start();
        System.err.println("msgReceiver is Running");
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
                Message msg = setMsg(dstId, itemId, itemQuantity, "StockCheckRequest", NULL_AUTH_CODE);
                sendMsg(i, msg);
            }
        }
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
}
