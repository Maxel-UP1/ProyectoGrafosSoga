package controlerView;

import controlers.LoginController;
import controlers.OwnerAccountController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserViewWindowController {



    private LoginController loginController;
    private OwnerAccountController ownerAccountController;
    public TextField txtNamePack;
    public ComboBox boxDeliveryAddres;
    public TextField txtIdCancelPack;
    public Label lblInfoCancell;
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

    //llena el vbox
    public void viewAdrees(ActionEvent actionEvent) {

    }
}
