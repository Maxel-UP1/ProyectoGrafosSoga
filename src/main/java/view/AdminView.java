package view;

import controlerView.AdminViewController;
import controlerView.UserViewWindowController;
import controlers.LoginController;
import controlers.UserAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminView extends Application {

    private LoginController loginController;
    private UserAccountController userAccountController;

    public AdminView(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //userAccountController.chargeOwnersReadFile("users");

        FXMLLoader fxmlLoader = new FXMLLoader(UserView.class.getResource("AdminView.fxml"));
        AdminViewController adminViewController = new AdminViewController(userAccountController, loginController);

        fxmlLoader.setController(adminViewController);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 625, 500);

        stage.setTitle("Menu Deliver");
        stage.setScene(scene);
        stage.show();

    }

}
