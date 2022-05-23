package domain.payment;

public class Card {
    public static final int CARD_NUM_LENGTH = 10;

    private final String cardNum;
    private final int cardPwd;
    private long money;

    public Card(String cardNum, int cardPwd) {
        this(cardNum, cardPwd,0);
    }

    public Card(String cardNum, int cardPwd, long money) {
        this.cardNum = cardNum;
        this.cardPwd = cardPwd;
        this.money = money;
    }

    public String getCardNum() {
        return cardNum;
    }
    public int getCardPwd() { return cardPwd; }
    public long getMoney() {
        return money;
    }
    public void setMoney(long money) {
        this.money = money;
    }
}