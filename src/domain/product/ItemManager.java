package domain.product;
import domain.payment.*;

public class ItemManager {
    private static final int MAX_ITEM = 20;
    Item[] items = new Item[MAX_ITEM];

    public ItemManager() {
    }

    public boolean checkStock(int itemId, int itemQuantity) {
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i] != null && items[i].getItemId() == itemId) {
                return items[i].getItemQuantity() >= itemQuantity;
            }
        }
        return false;
    }

    public void updateStockInfo(int itemId, int itemQuantity) {
        updateQuantity(itemId, -itemQuantity);
    }

    public void updateQuantity(int itemId, int itemQuantity) {
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i] != null && items[i].getItemId() == itemId) {
                items[i].setItemQuantity(items[i].getItemQuantity() + itemQuantity);
                break;
            }
        }
    }

    public boolean checkProduct(int itemId) {
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i] != null && items[i].getItemId() == itemId) {
                return true;
            }
        }
        return false;
    }

    public void synchronize(int itemId, int itemQuantity, String verification) {
        boolean verificationValidity = false;
        VerificationManager v = new VerificationManager();
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i] != null && items[i].getItemId() == itemId) {
                if (items[i].getItemQuantity() >= itemQuantity) {
                    items[i].setItemQuantity(items[i].getItemQuantity() - itemQuantity);
                    verificationValidity = true;
                }
                break;
            }
        }
        v.saveVerification(itemId, itemQuantity, verification, verificationValidity);
    }
}
