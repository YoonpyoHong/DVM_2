package domain.payment;

public class Verification {
    private final int itemId;
    private final int itemQuantity;
    private final String verificationCode;
    private final boolean verificationValidity;

    public Verification(int itemId, int itemQuantity, String verificationCode, boolean verificationValidity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.verificationCode = verificationCode;
        this.verificationValidity = verificationValidity;
    }

    public boolean getVerificationValidity() {
        return verificationValidity;
    }
    public int getItemId() { return itemId; }
    public int getItemQuantity() { return itemQuantity; }
    public String getVerificationCode() { return verificationCode; }
}
