package view;

import controlerView.UserViewWindowController;
import controlers.LoginController;
import controlers.OwnerAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserView extends Application {
    private LoginController loginController;
    private OwnerAccountController ownerAccountController;

    public UserView(OwnerAccountController ownerAccountController, LoginController loginController) {
        this.ownerAccountController = ownerAccountController;
        this.loginController = loginController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        ownerAccountController.chargeOwnersReadFile("users");

        FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getResource("UserView.fxml"));
        UserViewWindowController employeeWindowControler = new UserViewWindowController(ownerAccountController, loginController);

        fxmlLoader.setController(employeeWindowControler);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 615, 415);

        stage.setTitle("Menu User");
        stage.setScene(scene);
        stage.show();

    }

}
