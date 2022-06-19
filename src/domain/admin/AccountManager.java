package domain.admin;

public class AccountManager {
    private static final String PASSWORD = "1234";

    public AccountManager() {
        System.out.println(this.getClass() + "(): PASSWORD: " + PASSWORD);
        System.out.println(this.getClass() + " created.");

    }

    public boolean verifyLoginInfo(String password) {
        return PASSWORD.equals(password);
    }
}