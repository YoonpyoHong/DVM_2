package domain.payment;

import java.util.*;

public class VerificationManager {
    static final int CODE_LEN = 10;
    static final int LETTER = 26 + 10;
    static final String letterSet = "0123456789qwertyuiopasdfghjklzxcvbnm";
    static Random rand;
    static Map<String, Verification> verifications;

    public VerificationManager() {
        rand = new Random(System.currentTimeMillis());
        verifications = new HashMap<>();
    }

    private boolean isValidCode(String code) {
        boolean isAlpha = false;
        boolean isDigit = false;
        for (char ch : code.toCharArray()) {
            isAlpha |= Character.isAlphabetic(ch);
            isDigit |= Character.isDigit(ch);
        }
        return isAlpha && isDigit;
    }

    public String createVerificationCode() {
        StringBuilder verificationCode;
        do {
            verificationCode = new StringBuilder();
            for (int i = 0; i < CODE_LEN; i++) {
                verificationCode.append(letterSet.charAt(rand.nextInt(LETTER)));
            }
        } while (!isValidCode(verificationCode.toString()));
        return verificationCode.toString();
    }

    public void saveVerification(int itemId, int itemQuantity, String verificationCode, boolean verificationValidity) {
        verifications.put(verificationCode, new Verification(itemId, itemQuantity, verificationCode, verificationValidity));
    }

    public boolean checkVerification(String verificationCode) {
        return verifications.containsKey(verificationCode);
    }
}