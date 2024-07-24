package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import javafx.scene.control.Button;

public class AdminViewController {

    private LoginController loginController;
    private UserAccountController userAccountController;

    public AdminViewController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
    }

    public Button btnSeePacks;
}
