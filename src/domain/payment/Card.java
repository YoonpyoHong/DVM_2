package domain.payment;

public class Card {
    public static final int CARD_NUM_LENGTH = 16;

    private final String cardNum;
    private final int cardPwd;
    private int money;

    public Card(String cardNum, int cardPwd) {
        this(cardNum, cardPwd,0);
    }

    public Card(String cardNum, int cardPwd, int money) {
        this.cardNum = cardNum;
        this.cardPwd = cardPwd;
        this.money = money;
    }

    public int getCardPwd() { return cardPwd; }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) { this.money = money; }

    @Override
    public String toString() {
        return String.format("card: (%s, %d, %d)", cardNum, cardPwd, money);
    }
}