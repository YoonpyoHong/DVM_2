package domain.product;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private int itemId;
    private String itemName;
    private int itemPrice;
    private final AtomicInteger itemQuantity = new AtomicInteger();
    private final boolean onSale;

    public Item(int itemId, String itemName, int itemPrice, int itemQuantity, boolean onSale){
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity.set(itemQuantity);
        this.onSale = onSale;
    }

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getItemQuantity(){
        return itemQuantity.get();
    }
    public void setItemQuantity(int itemQuantity){
        this.itemQuantity.set(itemQuantity);
    }
    public String getItemName(){ return itemName; }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public int getItemPrice(){
        return itemPrice;
    }
    public void setItemPrice(int itemPrice){
        this.itemPrice = itemPrice;
    }
    public boolean getOnSale() { return onSale; }

    @Override
    public String toString() {
        return String.format("item: (%d, %s, %d, %d, %s)", itemId, itemName, itemPrice, itemQuantity.get(), onSale);
    }
}
