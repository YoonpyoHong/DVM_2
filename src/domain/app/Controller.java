package domain.app;

import domain.admin.AccountManager;
import domain.message.MessageManager;
import domain.payment.CardManager;
import domain.payment.PaymentManager;
import domain.payment.VerificationManager;
import domain.product.Item;
import domain.product.ItemManager;

public class Controller {
    private final PaymentManager paymentManager;
    private final CardManager cardManager;
    private final ItemManager itemManager;
    private final AccountManager accountManager;
    private final VerificationManager verificationManager;
    private final MessageManager messageManager;
    private static Item[] itemArray;

    private static final Controller instance = new Controller();

    private Controller() {
        cardManager = new CardManager();
        itemManager = new ItemManager();
        verificationManager = new VerificationManager();
        accountManager = new AccountManager();
        paymentManager = new PaymentManager();
        messageManager = new MessageManager();
        messageManager.start(); // for Message receive
        itemArray = itemManager.getItemList();
        System.out.println(this.getClass() + " created.");
    }

    public static Controller getInstance() {
        return instance;
    }

    // itemId: 0-indexed
    public String selectItem(int itemId, int itemQuantity, int[] dvmInfo) {
        System.out.printf("%s(%d, %d, {})%n", "Controller.selectItem", itemId, itemQuantity);
        if (itemManager.checkStock(itemId, itemQuantity)){
            System.out.println("Controller.selectItem(): " + itemArray[itemId] + " is in local vm");
            return "displayPayment";
        }
        int[] otherDvmInfo = messageManager.checkStockOfOtherVM(itemId, itemQuantity);
        System.arraycopy(otherDvmInfo, 0, dvmInfo, 0, dvmInfo.length);
        if (dvmInfo[0] == -1) {
            System.out.println(itemArray[itemId] + " is not in other vm");
            return "error: stock not available";
        }
        System.out.println(itemArray[itemId] + " is in other vm");
        return "displayPrepayment";
    }

    public String payment(int itemId, int itemQuantity, String cardNum, int cardPwd) {
        System.out.printf("payment(%d, %d, %s, %d)%n", itemId, itemQuantity, cardNum, cardPwd);
        boolean cardValidity = cardManager.checkCardValidity(cardNum, 1234);
        if (!cardValidity) {
            return "error: invalid card";
        }
        int totalPrice = itemArray[itemId].getItemPrice() * itemQuantity;
        boolean paymentSuccess = paymentManager.payment(cardManager, totalPrice, cardNum);
        if (!paymentSuccess) {
            return "payment error: insufficient balance.";
        }
        boolean stockAvailable = itemManager.checkStock(itemId, itemQuantity);
        if (!stockAvailable) {
            paymentManager.cancelPayment(cardManager, totalPrice, cardNum);
            return "error: no item stock. cancel payment";
        }
        itemManager.updateStockInfo(itemId, itemQuantity);
        return "payment complete";
    }

    public String prepayment(int itemId, int itemQuantity, String cardNum, int cardPwd, int destinationId) {
        boolean cardValidity = cardManager.checkCardValidity(cardNum, cardPwd);
        if (!cardValidity) {
            return "error: invalid card";
        }
        int totalPrice = itemId * itemQuantity;
        boolean paymentSuccess = paymentManager.payment(cardManager, totalPrice, cardNum);
        if (!paymentSuccess) {
            return "payment error in prepayment";
        }
        String authenticationCode = verificationManager.createVerificationCode();
        messageManager.sendPrepaymentInfo(itemId, itemQuantity, destinationId, authenticationCode);
        return "prepayment complete";
    }

    public AccountManager getAccountManager() { return accountManager; }
    public ItemManager getItemManager() { return itemManager; }
    public CardManager getCardReader() { return cardManager; }
    public PaymentManager getPaymentManager() { return paymentManager; }
    public VerificationManager getVerificationManager() { return verificationManager; }
}