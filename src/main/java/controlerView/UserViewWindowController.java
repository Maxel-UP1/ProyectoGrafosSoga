package controlerView;

import controlers.LoginController;
import controlers.OwnerAccountController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class UserViewWindowController {


    private LoginController loginController;
    private OwnerAccountController ownerAccountController;
    public Label lblNameUser;
    public Button btnCreatePack;
    public Button brnSeePacks;
    public Button btnCancellPack;


    public UserViewWindowController(OwnerAccountController ownerAccountController, LoginController loginController) {
        this.ownerAccountController = ownerAccountController;
        this.loginController = loginController;
    }

    public void addPack(ActionEvent actionEvent) {
    }

    public void seePacks(ActionEvent actionEvent) {
    }

    public void cancelPack(ActionEvent actionEvent) {
    }
}
