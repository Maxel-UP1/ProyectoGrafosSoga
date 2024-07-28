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
            System.out.println("Packages list loaded: " + packagesList.size());
            for (Package pack : packagesList) {
                System.out.println(pack.getId() + " " + pack.getName() + " " + pack.getAddress() + " " + pack.getStatus());
            }
        }
    }

