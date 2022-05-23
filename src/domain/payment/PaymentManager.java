package domain.payment;

public class PaymentManager {

    public PaymentManager() {
    }

    public boolean payment(CardReader cardReader, long totalPrice, String cardNum) {
        Card card = cardReader.getCardInfo(cardNum);
        if (card.getMoney() < totalPrice) {
            return false;
        }
        card.setMoney(card.getMoney() - totalPrice);
        return true;
    }

    public void cancelPayment(CardReader cardReader, long totalPrice, String cardNum) {
        Card card = cardReader.getCardInfo(cardNum);
        card.setMoney(card.getMoney() + totalPrice);
    }
}