package domain.payment;

import domain.product.Item;

public class Order {
    private Item item;
    private int quantity;

    public Order(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }
    public int getQuantity() {
        return quantity;
    }
}
