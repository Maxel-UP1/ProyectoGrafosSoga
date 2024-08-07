package view;

import controlerView.AdminViewController;
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
        FXMLLoader fxmlLoader = new FXMLLoader(AdminView.class.getResource("AdminView.fxml"));
        AdminViewController adminViewController = new AdminViewController(userAccountController, loginController);

        fxmlLoader.setController(adminViewController);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Menu Deliver");
        stage.setScene(scene);
        adminViewController.showNameUserLoged();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
