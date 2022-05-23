package domain.product;
import domain.payment.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ItemManager {
    public static final int MAX_ITEM = 20;
    public static final int MAX_LOCAL_ITEM = 7;

    private static Item[] items;

    public ItemManager() {
        loadItemList();
    }

    public boolean checkStock(int itemId, int itemQuantity) {
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i].getItemId() == itemId) {
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
            if (items[i].getItemId() == itemId) {
                items[i].setItemQuantity(items[i].getItemQuantity() + itemQuantity);
                return;
            }
        }
    }

    public boolean checkProduct(int itemId) {
        for (int i = 0; i < MAX_ITEM; i++) {
            if (items[i].getItemId() == itemId) {
                return true;
            }
        }
        return false;
    }

    /* TODO: has to be modified due to race conditions. */
    public void synchronize(int itemId, int itemQuantity, String verificationCode) {
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
        v.saveVerification(itemId, itemQuantity, verificationCode, verificationValidity);
    }

    public Item[] getItemList() {
        return items;
    }

    private void loadItemList() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/domain/product/itemList.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        Scanner in = new Scanner(inputStream);
        int i = 0;
        items = new Item[MAX_ITEM];
        while (in.hasNext()) {
            String[] argv = in.nextLine().split(" ", 3);
            items[i++] = new Item(i, argv[0], Integer.parseInt(argv[1]), Integer.parseInt(argv[2]));
        }
    }
}
