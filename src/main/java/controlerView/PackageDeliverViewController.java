package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import model.Package;

import java.util.ArrayList;

public class PackageDeliverViewController {
    private ArrayList<Package> packagesList;
    private LoginController loginController;
    private UserAccountController userAccountController;

    public PackageDeliverViewController(UserAccountController userAccountController, LoginController loginController) {
            this.userAccountController = userAccountController;
            this.loginController = loginController;
            this.packagesList = userAccountController.getPackagesList();
    }




}

