package domain.admin;

import java.util.*;

public class AccountManager {


    public AccountManager() {
    
    }
    private String password = "abcdefg";

    public Boolean verifyLoginInfo(String password) {
        Boolean passwordValidity = false;
        if(password == this.password){
            passwordValidity =true;
        }
        return passwordValidity;
    }

}