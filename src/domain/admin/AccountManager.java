package domain.admin;

public class AccountManager {
    private static final String PASSWORD = "abcdefg";

    public AccountManager() {
    }

    public boolean verifyLoginInfo(String password) {
        return password.equals(PASSWORD);
    }
}