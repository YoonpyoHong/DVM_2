package domain.product;

import domain.Controller;
import domain.payment.VerificationManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class ItemManager {
    public static final int MAX_ITEM_QUANTITY = 999;
    public static final int MAX_ITEM = 20;
    public static final int MAX_LOCAL_ITEM = 7;

    private static Item[] itemArray;

    public ItemManager() {
        loadItemList();
        System.out.println(this.getClass() + " created.");
    }

    public boolean checkStock(int itemId, int itemQuantity) {
        boolean available = itemArray[itemId].getItemQuantity() >= itemQuantity;
        System.out.printf("ItemManager.checkStock(%d, %d): %s%n", itemId, itemQuantity, available);
        return available;
    }

    public void updateStockInfo(int itemId, int itemQuantity) {
        updateQuantity(itemId, -itemQuantity);
    }

    public void updateQuantity(int itemId, int itemQuantity) {
        assert 0 <= itemId && itemId < MAX_ITEM;
        itemArray[itemId].setItemQuantity(itemArray[itemId].getItemQuantity() + itemQuantity);
    }

    public boolean checkProduct(int itemId) {
        assert 0 <= itemId && itemId < MAX_ITEM;
        return itemArray[itemId].getOnSale();
    }

    public void synchronize(int itemId, int itemQuantity, String verificationCode) {
        boolean verificationValidity = false;
        if (itemArray[itemId].getItemQuantity() >= itemQuantity) {
            itemArray[itemId].setItemQuantity(itemArray[itemId].getItemQuantity() - itemQuantity);
            verificationValidity = true;
        }
        VerificationManager v = Controller.getInstance().getVerificationManager();
        v.saveVerification(itemId, itemQuantity, verificationCode, verificationValidity);
    }

    public Item[] getItemList() {
        return itemArray;
    }

    public void showItemList() {
        for (int i = 0; i < MAX_ITEM; i++) {
            System.out.println(itemArray[i]);
        }
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
        itemArray = new Item[MAX_ITEM];
        while (in.hasNext()) {
            String[] argv = in.nextLine().split(" ", 4);
            itemArray[i] = new Item(i + 1, argv[0], Integer.parseInt(argv[1]), Integer.parseInt(argv[2]), Boolean.parseBoolean(argv[3]));
            System.out.println("ItemManager.loadItemList(): " + itemArray[i] + " has been added.");
            i += 1;
        }
    }
}
