package domain.payment;

public class PaymentManager {

    public PaymentManager() {
        System.out.println(this.getClass() + " created.");
    }

    public boolean payment(CardManager cardManager, int totalPrice, String cardNum) {
        System.out.println("totalPrice = " + totalPrice);
        Card card = cardManager.getCardInfo(cardNum);
        System.out.println("payment(): before " + card);
        if (card.getMoney() < totalPrice) {
            return false;
        }
        card.setMoney(card.getMoney() - totalPrice);
        System.out.println("payment(): after " + card);
        return true;
    }

    public void cancelPayment(CardManager cardManager, int totalPrice, String cardNum) {
        Card card = cardManager.getCardInfo(cardNum);
        System.out.println("cancelPayment(): before " + card);
        card.setMoney(card.getMoney() + totalPrice);
        System.out.println("cancelPayment(): after " + card);
    }
}