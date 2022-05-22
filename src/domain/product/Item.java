package domain.product;

public class Item {
    private int itemId;
    private int itemQuantity;
    private String itemName;
    private int itemPrice;

    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId){
        this.itemId = itemId;
    }

    public int getItemQuantity(){
        return itemQuantity;
    }
    public void setItemQuantity(int itemQuantity){
        this.itemQuantity = itemQuantity;
    }

    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public int getItemPrice(){
        return itemPrice;
    }
    public void setItemPrice(int itemPrice){
        this.itemPrice = itemPrice;
    }

    public Item(int itemId, int itemQuantity, String itemName, int itemPrice){
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
