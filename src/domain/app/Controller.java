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
        System.err.println(this.getClass() + " created");
    }

    public static void turnMachineOn() {}

    public String selectItem(int itemId, int itemQuantity, int[] dvmInfo) {
        if (itemManager.checkStock(itemId, itemQuantity)){
            System.out.println(items[itemId] + " is in local vm");
            return "displayPayment";
        }
        dvmInfo = messageManager.checkStockOfOtherVM(itemId, itemQuantity);
        if (dvmInfo[0] == -1) {
            System.out.println(items[itemId] + " is not in other vm");
            return "stock not available";
        }
        System.out.println(items[itemId] + " is in other vm");
        return "displayPrepayment";
    }

    public String payment(int itemId, int itemQuantity, String cardNum, int cardPwd) {
        boolean cardValidity = cardReader.checkCardValidity(cardNum, 1234);
        if (!cardValidity) {
            return "invalid card";
        }
        long totalPrice = (long) itemId * itemQuantity;
        boolean paymentSuccess = paymentManager.payment(cardReader, totalPrice, cardNum);
        if (!paymentSuccess) {
            return "payment error";
        }
        boolean stockAvailable = itemManager.checkStock(itemId, itemQuantity);
        if (!stockAvailable) {
            paymentManager.cancelPayment(cardReader, totalPrice, cardNum);
            return "no item stock. cancle payment";
        }
        itemManager.updateStockInfo(itemId, itemQuantity);
        return "paytment complete";
    }

    public String comfirmVerification(String authCode) {
        boolean verificationAvailable = verificationManager.checkVerification(authCode);
        if (!verificationAvailable) {
            return "invalid auth Code";
        }
        Verification verification = verificationManager.getVerification(authCode);
        if (verification.getVerificationValidity()) {
            return "valid prepayment";
        }
        return "invalid prepayment";
    }

    public Item[] getItemList() { return itemManager.getItemList(); }

    public AccountManager getAccountManager() { return accountManager; }
    public MessageManager getMsgManager() { return messageManager; }
    public ItemManager getItemManager() { return itemManager; }

    public CardReader getCardReader() { return cardReader; }
}