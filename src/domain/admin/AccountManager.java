package domain.admin;

public class AccountManager {
    private static final String PASSWORD = "1";

    public AccountManager() {
        System.out.println(this.getClass() + " created.");
    }

    public boolean verifyLoginInfo(String password) {
        return PASSWORD.equals(password);
    }
}