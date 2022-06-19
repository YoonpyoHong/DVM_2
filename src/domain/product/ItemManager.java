package domain.product;

import domain.payment.VerificationManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ItemManager {
    public static final int MAX_ITEM_QUANTITY = 999;
    public static final int MAX_ITEM = 20;
    public static final int MAX_LOCAL_ITEM = 7;

    private static Item[] items;

    public ItemManager() {
        loadItemList();
        System.out.println(this.getClass() + " created.");
    }

    public boolean checkStock(int itemId, int itemQuantity) {
        return items[itemId].getItemQuantity() >= itemQuantity;
    }

    public void updateStockInfo(int itemId, int itemQuantity) {
        updateQuantity(itemId, -itemQuantity);
    }

    public void updateQuantity(int itemId, int itemQuantity) {
        assert 0 <= itemId && itemId < MAX_ITEM;
        items[itemId].setItemQuantity(items[itemId].getItemQuantity() + itemQuantity);
    }

    public boolean checkProduct(int itemId) {
        assert 0 <= itemId && itemId < MAX_ITEM;
        return items[itemId].getOnSale();
    }

    public void synchronize(int itemId, int itemQuantity, String verificationCode) {
        assert 0 <= itemId && itemId < MAX_ITEM;
        boolean verificationValidity = false;
        VerificationManager v = new VerificationManager();
        if (items[itemId].getItemQuantity() >= itemQuantity) {
            items[itemId].setItemQuantity(items[itemId].getItemQuantity() - itemQuantity);
            verificationValidity = true;
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
            String[] argv = in.nextLine().split(" ", 4);
            items[i] = new Item(i + 1, argv[0], Integer.parseInt(argv[1]), Integer.parseInt(argv[2]), Boolean.parseBoolean(argv[3]));
            System.out.println("ItemManager.loadItemList(): " + items[i] + " has been added.");
            i += 1;
        }
    }

    public void showItemList() {
        for (int i = 0; i < MAX_ITEM; i++) {
            System.out.println(items[i]);
        }
    }
}
