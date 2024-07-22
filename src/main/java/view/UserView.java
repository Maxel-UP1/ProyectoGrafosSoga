package view;

import controlerView.UserViewWindowController;
import controlers.LoginController;
import controlers.UserAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserView extends Application {
    private LoginController loginController;
    private UserAccountController userAccountController;

    public UserView(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //userAccountController.chargeOwnersReadFile("users");

        FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getResource("UserView.fxml"));
        UserViewWindowController employeeWindowControler = new UserViewWindowController(userAccountController, loginController);

        fxmlLoader.setController(employeeWindowControler);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 625, 500);

        stage.setTitle("Menu User");
        stage.setScene(scene);
        stage.show();

    }

}
