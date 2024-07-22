package controlers;

import model.Account;
import model.User;

import java.util.ArrayList;

public class LoginController {
    private UserAccountController userAccountController;
    private Account AccountLogged;

    public Account getAccountLogged() {
        return AccountLogged;
    }

    public LoginController(UserAccountController userAccountController) {
        this.userAccountController = userAccountController;
    }

    public boolean login(String nameAcountUser, String password) {
        ArrayList<User> users = userAccountController.getUsersList();
        for (User user : users) {
            if (user.getAccount().getNameAcount().equals(nameAcountUser) && user.getAccount().getPassword().equals(password)) {
                AccountLogged = user.getAccount();
                return true;
            }
        }
        return false;
    }


}



