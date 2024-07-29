package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class UserPackageViewController {
    public TableView tablePackages;
    public TableColumn colPackageId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colStatus;
    public Button btnBack;
    private UserAccountController userAccountController;
    private LoginController loginController;

    public UserPackageViewController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
    }

    public void goBack(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }




}
