package domain.payment;

import java.util.*;

public class VerificationManager {
    Random rand;

    public VerificationManager() {
        rand = new Random(System.currentTimeMillis());
    }

    public int requestVerificationCode() {
        // TODO implement here
        return 0;
    }

    public void createVerificationCode() {
        return;
    }

    public void saveVerification(Integer itemId, Integer itemQuantity, String verification, Boolean verificationvalidity) {
        // TODO implement here
        return;
    }
    public Boolean checkVerification(String verification) {
        // TODO implement here
        return null;
    }
}