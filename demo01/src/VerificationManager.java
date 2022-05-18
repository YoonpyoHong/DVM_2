
import java.util.*;

/**
 * 
 */
public class VerificationManager {

    /**
     * Default constructor
     */
    public VerificationManager() {
    }

    /**
     * 
     */
    public int itemId;

    /**
     * 
     */
    public int itemQuantity;

    /**
     * 
     */
    public int verification;

    /**
     * 
     */
    public Boolean verificationValidity;

    /**
     * @return
     */
    public int requestVerificationCode() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public void createVerificationCode() {
        // TODO implement here
        return;
    }

    /**
     * @param itemId 
     * @param itemQuantity 
     * @param verification 
     * @param verificationvalidity 
     * @return
     */
    public void saveVerification(Integer itemId, Integer itemQuantity, String verification, Boolean verificationvalidity) {
        // TODO implement here
        return;
    }

    /**
     * @param verification 
     * @return
     */
    public Boolean checkVerification(String verification) {
        // TODO implement here
        return null;
    }

}