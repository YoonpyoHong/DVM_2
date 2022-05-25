package domain.payment;

public class PaymentManager {

    public PaymentManager() {
        System.out.println(this.getClass() + " created.");
    }

    public boolean payment(CardReader cardReader, int totalPrice, String cardNum) {
        System.out.println("totalPrice = " + totalPrice);
        Card card = cardReader.getCardInfo(cardNum);
        System.out.println("payment(): before " + card);
        if (card.getMoney() < totalPrice) {
            return false;
        }
        card.setMoney(card.getMoney() - totalPrice);
        System.out.println("payment(): after " + card);
        return true;
    }

    public void cancelPayment(CardReader cardReader, int totalPrice, String cardNum) {
        Card card = cardReader.getCardInfo(cardNum);
        System.out.println("cancelPayment(): before " + card);
        card.setMoney(card.getMoney() + totalPrice);
        System.out.println("cancelPayment(): after " + card);
    }
}