package domain.app;

import domain.payment.CardReader;
import domain.payment.PaymentManager;
import ui.Window_3_1;

import java.util.*;

public class Controller {
    public String errorType;
    private final PaymentManager paymentManager;
    private final CardReader cardReader;

    public Controller() {
        cardReader = new CardReader();
        paymentManager = new PaymentManager();
    }

    public static void turnMachineOn() {
        return;
    }

    public void selectItem() {
        return;
    }

    public void selectQuantity(Integer itemQuantity) {
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

    public Boolean checkStock(Integer itemId, Integer itemQuantity) {
        // TODO implement here
        return null;
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
        // TODO implement here
        return;
    }

    public void logout() {
        // TODO implement here
        return;
    }

    public void inputItem(Integer itemId, Integer itemQuantity) {
        // TODO implement here
        return;
    }

    public void displayChangeMsg() {
        // TODO implement here
        return;
    }

    public void displayPrepayment() {
        // TODO implement here
        return;
    }

}