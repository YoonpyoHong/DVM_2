package domain.app;

import domain.admin.AccountManager;
import domain.message.MessageManager;
import domain.product.Item;
import domain.product.ItemManager;
import domain.payment.CardReader;
import domain.payment.PaymentManager;


public class Controller {
    public String errorType;
    private final PaymentManager paymentManager;
    private final CardReader cardReader;
    private final ItemManager itemManager;
    private final AccountManager accountManager;
    private final MessageManager messageManager;

    public Controller() {
        cardReader = new CardReader();
        paymentManager = new PaymentManager();
        itemManager = new ItemManager();
        messageManager = new MessageManager(itemManager);
//        messageManager.run();
        accountManager = new AccountManager();
        System.err.println(this.getClass() + " created");
    }

    public static void turnMachineOn() {}

    public void selectItem(int itemId, int itemQuantity) {
        int dvmInfo[] = {0,0,0};
        if (itemManager.checkStock(itemId, itemQuantity)){
            displayPayment();
        }
        /*
        else{
           dvmInfo =  messageManager.checkStockofOtherVM(itemId, itemQuantity);
           if(dvmInfo[0] == -1){
               displayErrorMsg("stock is not available");
           }
           else{
               displayPrepayment();
           }
        }
        */
    }

    public void displayItemSelection() {
        // TODO implement here
    }

    public void displayPayment() {

    }

    public boolean inputCardInfo(String cardNum, int cardPwd) {
        return false;
    }

    public String displayErrorMsg(String errorType) {
        return errorType;
    }

    public String displayMsg() {
        // TODO implement here
        return "";
    }

    public void cancelPayment() {
        // TODO implement here
    }

    public void inputVerification(String verification) {
        // TODO implement here
    }

    public void displayMain() {
        // TODO implement here
    }

    public void homeButton() {
        // TODO implement here
    }

    public void login(String password) {
        if (accountManager.verifyLoginInfo(password)){
            displayAdminPage();
        }
    }

    public void displayAdminPage() {
    }

    public void logout() {
        // TODO implement here
    }

    public void inputItem(int itemId, int itemQuantity) {
        itemManager.updateQuantity(itemId, itemQuantity);
    }

    public void displayChangeMsg() {

    }

    public void displayPrepayment() {

    }

    public Item[] getItemList() { return itemManager.getItemList(); }

    public AccountManager getAccountManager() { return accountManager; }
    public MessageManager getMsgManager() { return messageManager; }
    public ItemManager getItemManager() { return itemManager; }
}