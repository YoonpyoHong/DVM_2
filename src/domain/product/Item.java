package domain.product;

import java.util.concurrent.atomic.AtomicInteger;

import static domain.product.ItemManager.MAX_ITEM;

public class Item {
    private final int itemId; // 1-indexed
    private final String itemName;
    private final int itemPrice;
    private final AtomicInteger itemQuantity = new AtomicInteger();
    private final boolean onSale;

    public Item(int itemId, String itemName, int itemPrice, int itemQuantity, boolean onSale){
        assert 1 <= itemId && itemId <= MAX_ITEM;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity.set(itemQuantity);
        this.onSale = onSale;
    }

    public int getItemId() {
        return itemId;
    }

    public int getItemQuantity(){
        return itemQuantity.get();
    }
    public void setItemQuantity(int itemQuantity){
        this.itemQuantity.set(itemQuantity);
    }
    public String getItemName(){ return itemName; }

    public int getItemPrice(){
        return itemPrice;
    }

    public boolean getOnSale() { return onSale; }

    @Override
    public String toString() {
        return String.format("item: (%02d, %s, %d, %d, %s)", itemId, itemName, itemPrice, itemQuantity.get(), onSale);
    }
}
