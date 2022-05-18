package domain.payment;

public class Card {
    private final String cardNum;
    private final int cardPwd;
    private long money;

    public Card(String cardNum, int cardPwd, long money) {
        this.cardNum = cardNum;
        this.cardPwd = cardPwd;
        this.money = money;
    }

    public Card(String cardNum, int cardPwd) {
        this(cardNum, cardPwd,0);
    }

    public String getCardNum() {
        return cardNum;
    }

    public long getMoney() {
        return money;
    }

    public int getCardPwd() { return cardPwd; }

    public void setMoney(long money) {
        this.money = money;
    }

}