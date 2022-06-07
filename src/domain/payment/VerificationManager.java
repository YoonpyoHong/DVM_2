package domain.payment;

import java.util.*;

public class VerificationManager {
    public static final int CODE_LENGTH = 10;
    private static final int LETTER = 26 + 10;
    private static final String letterSet = "0123456789qwertyuiopasdfghjklzxcvbnm";
    private static Random rand;
    private static Map<String, Verification> verifications;

    public VerificationManager() {
        rand = new Random(System.currentTimeMillis());
        verifications = new HashMap<>();
        loadTestVerification();
        System.out.println(this.getClass() + " created.");
    }

    private void loadTestVerification() {
        saveVerification(1, 1, "123456789a", true);
        saveVerification(1, 1, "123456789b", false);
    }

    public String createVerificationCode() {
        StringBuilder verificationCode;
        do {
            verificationCode = new StringBuilder();
            for (int verificationPosition = 0; verificationPosition < CODE_LENGTH; verificationPosition++) {
                verificationCode.append(letterSet.charAt(rand.nextInt(LETTER)));
            }
        } while (!isValidCode(verificationCode.toString()));
        return verificationCode.toString();
    }

    public void saveVerification(int itemId, int itemQuantity, String verificationCode, boolean verificationValidity) {
        Verification verification = new Verification(itemId, itemQuantity, verificationCode, verificationValidity);
        System.out.println(verification + " has been added.");
        verifications.put(verificationCode, verification);
    }

    public Verification checkVerification(String verificationCode) {
        return verifications.get(verificationCode);
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

    public void removeVerification(String verificationCode) {
        System.out.println(verifications.remove(verificationCode) + " has been removed.");
    }
}