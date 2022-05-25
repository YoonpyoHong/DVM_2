package domain.app;

import domain.admin.AccountManager;
import domain.message.MessageManager;
import domain.payment.Verification;
import domain.payment.VerificationManager;
import domain.product.Item;
import domain.product.ItemManager;
import domain.payment.CardReader;
import domain.payment.PaymentManager;

public class Controller {
    private final PaymentManager paymentManager;
    private final CardReader cardReader;
    private final ItemManager itemManager;
    private final AccountManager accountManager;
    private final MessageManager messageManager;
    private final VerificationManager verificationManager;
    private static Item[] items;

    public Controller() {
        cardReader = new CardReader();
        paymentManager = new PaymentManager();
        itemManager = new ItemManager();
        messageManager = new MessageManager(itemManager);
        accountManager = new AccountManager();
        verificationManager = new VerificationManager();
        items = itemManager.getItemList();
        messageManager.start();
        System.out.println(this.getClass() + " created.");
    }

    public static void turnMachineOn() {}

    public String selectItem(int itemId, int itemQuantity, int[] dvmInfo) {
        if (itemManager.checkStock(itemId, itemQuantity)){
            System.out.println(items[itemId] + " is in local vm");
            return "displayPayment";
        }
        int[] resDvmInfo = messageManager.checkStockOfOtherVM(itemId, itemQuantity);
        System.arraycopy(resDvmInfo, 0, dvmInfo, 0, dvmInfo.length);
        if (dvmInfo[0] == -1) {
            System.out.println(items[itemId] + " is not in other vm");
            return "error: stock not available";
        }
        System.out.println(items[itemId] + " is in other vm");
        return "displayPrepayment";
    }

    public String payment(int itemId, int itemQuantity, String cardNum, int cardPwd) {
        System.out.printf("payment(%d, %d, %s, %d)%n", itemId, itemQuantity, cardNum, cardPwd);
        boolean cardValidity = cardReader.checkCardValidity(cardNum, 1234);
        if (!cardValidity) {
            return "error: invalid card";
        }
        int totalPrice = items[itemId].getItemPrice() * itemQuantity;
        boolean paymentSuccess = paymentManager.payment(cardReader, totalPrice, cardNum);
        if (!paymentSuccess) {
            return "payment error: insufficient balance.";
        }
        boolean stockAvailable = itemManager.checkStock(itemId, itemQuantity);
        if (!stockAvailable) {
            paymentManager.cancelPayment(cardReader, totalPrice, cardNum);
            return "error: no item stock. cancel payment";
        }
        itemManager.updateStockInfo(itemId, itemQuantity);
        return "payment complete";
    }

    public String prepayment(int itemId, int itemQuantity, String cardNum, int cardPwd, int dstId) {
        boolean cardValidity = cardReader.checkCardValidity(cardNum, 1234);
        if (!cardValidity) {
            return "error: invalid card";
        }
        int totalPrice = itemId * itemQuantity;
        boolean paymentSuccess = paymentManager.payment(cardReader, totalPrice, cardNum);
        if (!paymentSuccess) {
            return "payment error in prepayment";
        }
        String authCode = verificationManager.createVerificationCode();
        messageManager.sendPrepaymentInfo(itemId, itemQuantity, dstId, authCode);
        return "prepayment complete";
    }

    public Verification comfirmVerification(String authCode) {
        return verificationManager.checkVerification(authCode);
    }

    public Item[] getItemList() { return itemManager.getItemList(); }

    public AccountManager getAccountManager() { return accountManager; }
    public MessageManager getMsgManager() { return messageManager; }
    public ItemManager getItemManager() { return itemManager; }
    public CardReader getCardReader() { return cardReader; }
    public PaymentManager getPaymentManager() { return paymentManager; }
    public VerificationManager getVerificationManager() { return verificationManager; }
}