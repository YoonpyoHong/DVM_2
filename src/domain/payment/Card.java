package domain.payment;

public class Card {
    private final String card_num;
    private long money;

    public Card(String card_num, long money) {
        this.card_num = card_num;
        this.money = money;
    }

    public Card(String card_num) {
        this(card_num, 0);
    }

    public String getCardNum() {
        return card_num;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}