
import java.util.*;

/**
 * 
 */
public class ItemManager {

    /**
     * Default constructor
     */
    public ItemManager() {
    }

    public Boolean checkStock(Integer itemId, Integer itemQuantity) {
        Boolean stockValidity = false;
        int i = 0;
        while(item[i]==NULL) {
            if (item[i].itemId == itemId) {
                stockValidity = item[i].itemQuantity >= itemQuantity;
                break;
            }
            i++;
        }

        return stockValidity;
    }

    public void updateStockInfo(Integer itemId, Integer itemQuantity) {
        int i = 0;
        while(item[i]==null){
            if(item[i].itemId == itemId) {
                item[i].itemQuantity -= itemQuantity;
                break;
            }
            i++;
        }
        return null;
    }

    public Boolean checkProduct(Integer itemId) {
        Boolean itemValidity = false;
        int i = 0;
        while(item[i]==null){
            if(item[i].itemId == itemId) {
                itemValidity = item[i].itemQuantity >= itemQuantity;
                break;
            }
            i++;
        }
        return itemValidity;
    }

    public void synchronize(Integer itemId, Integer itemQuantity, String verification) {
        Boolean verificationvalidity = false;
        int i = 0;
        while(item[i]==null){
            if(item[i].itemId == itemId) {
                if(item[i].itemQuantity >= itemQuantity){
                    item[i].itemQuantity -= itemQuantity;
                    verificationvalidity = true;
                }
                break;
            }
            i++;
        }
        saveVerification(itemId,itemQuantity, verification, verificationvalidity);
        return null;
    }

    public void updateQuantity(Integer itemId, Integer itemQuantity) {

        while(item[i]==null){
            if(item[i].itemId == itemId) {
                item[i].itemQuantity += itemQuantity;
                break;
            }
            i++;
        }
        return null;
    }

}