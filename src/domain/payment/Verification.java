package domain.payment;

public class Verification {
    int itemId;
    int itemQuantity;
    String verificationCode;
    Boolean verificationValidity;

    public Verification(int itemId, int itemQuantity, String verificationCode, boolean verificationValidity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.verificationCode = verificationCode;
        this.verificationValidity = verificationValidity;
    }
}
