package domain.product;
import domain.payment.*;

public class ItemManager {
    private static final int MAX_ITEM = 20;
    private static final String[] drinkList = {"콜라", "사이다",
            "녹차", "홍차",
            "밀크티", "탄산수",
            "보리차", "캔커피",
            "물", "에너지드링크",
            "바닷물", "식혜",
            "아이스티", "딸기주스",
            "오렌지주스", "포도주스",
            "이온음료", "아메리카노",
            "핫초코", "카페라떼"
    };
    private static final int[] prices = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
    private static final int[] quantities = {999,999,999,999,999,999,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Item[] items = new Item[MAX_ITEM];

    public ItemManager() {
        for (int i = 0; i < MAX_ITEM; i++) {
            items[i] = new Item(i, quantities[i], drinkList[i], prices[i]);
        }
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
