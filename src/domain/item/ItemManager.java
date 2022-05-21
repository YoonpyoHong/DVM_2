package domain.item;
import java.util.*;
import domain.payment.*;

public class ItemManager {

        public ItemManager() {

        }
        Item[] item = new Item[20];


        public Boolean checkStock(Integer itemId, Integer itemQuantity) {
            Boolean stockValidity = false;

            int i = 0;
            while(item[i]==null) {
                if (item[i].itemId == itemId) {
                    stockValidity = item[i].getItemQuantity() >= itemQuantity;
                    break;
                }
                i++;
            }

            return stockValidity;
        }

        public void updateStockInfo(Integer itemId, Integer itemQuantity) {
            int i = 0;
            while(item[i]==null){
                if(item[i].getItemId() == itemId) {
                    item[i].setItemQuantity(item[i].getItemQuantity() - itemQuantity);
                    break;
                }
                i++;
            }
        }

        public Boolean checkProduct(Integer itemId) {
            Boolean itemValidity = false;
            int i = 0;
            while(item[i]==null){
                if(item[i].itemId == itemId) {
                    itemValidity = true;
                    break;
                }
                i++;
            }
            return itemValidity;
        }

        public void synchronize(Integer itemId, Integer itemQuantity, String verification) {
            Boolean verificationValidity = false;
            VerificationManager v = new VerificationManager();
            int i = 0;
            while(item[i]==null){
                if(item[i].getItemId() == itemId) {
                    if(item[i].getItemQuantity() >= itemQuantity){
                        item[i].setItemQuantity(item[i].getItemQuantity() - itemQuantity);
                        verificationValidity = true;
                    }
                    break;
                }
                i++;
            }
            v.saveVerification(itemId,itemQuantity, verification, verificationValidity);
        }

        public void updateQuantity(Integer itemId, Integer itemQuantity) {
            int i = 0;
            while(item[i]==null){
                if(item[i].getItemId() == itemId) {
                    item[i].setItemQuantity(item[i].getItemQuantity() + itemQuantity);
                    break;
                }
                i++;
            }
        }
}
