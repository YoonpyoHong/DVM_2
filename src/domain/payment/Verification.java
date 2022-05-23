package domain.payment;

public class Verification {
    private int itemId;
    private int itemQuantity;
    private String verificationCode;
    private boolean verificationValidity;

    public Verification(int itemId, int itemQuantity, String verificationCode, boolean verificationValidity) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.verificationCode = verificationCode;
        this.verificationValidity = verificationValidity;
    }
}
