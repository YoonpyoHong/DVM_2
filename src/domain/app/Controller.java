package domain.app;

import domain.admin.AccountManager;
import domain.item.ItemManager;
import domain.payment.CardReader;
import domain.payment.PaymentManager;
import ui.Window_3_1;

import java.util.*;

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
        messageManager = new MessageManager();
        accountManager = new AccountManager();
    }

    public static void turnMachineOn() {
        return;
    }

    public void selectItem(Integer itemId, Integer itemQuantity) {
        int dvmInfo[] = {0,0,0};
        if (itemManager.checkStock(itemId, itemQuantity)){
            displayPayment();
        }
        else{
           dvmInfo =  messageManager.checkStockofOtherVM(itemId, itemQuantity);
           if(dvmInfo[0] == -1){
               displayErrorMsg("stock is not available");
           }
           else{
               displayPrepayment();
           }
        }

    }

    public void displayItemSelection() {
        // TODO implement here
        return;
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
        return;
    }

    public void inputVerification(String verification) {
        // TODO implement here
        return;
    }

    public void displayMain() {
        // TODO implement here
        return;
    }

    public void homeButton() {
        // TODO implement here
        return;
    }

    public void login(String password) {
        if(accountManager.verifyLoginInfo(password)){
            displayAdminPage();
        }
    }

    public void displayAdminPage(){

    }

    public void logout() {
        // TODO implement here
        return;
    }

    public void inputItem(Integer itemId, Integer itemQuantity) {
        itemManager.updateQuantity(itemId, itemQuantity);
    }

    public void displayChangeMsg() {

        return;
    }

    public void displayPrepayment() {

        return;
    }

}