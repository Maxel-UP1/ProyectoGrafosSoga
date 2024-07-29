package view;

import controlerView.AdminViewController;
import controlerView.UserPackageViewController;
import controlers.LoginController;
import controlers.UserAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserPackagesView extends Application {
    private LoginController loginController;
    private UserAccountController userAccountController;

    public UserPackagesView(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminView.class.getResource("UserPackageView.fxml"));
        UserPackageViewController userPackageViewController = new UserPackageViewController(userAccountController, loginController);

        fxmlLoader.setController(userPackageViewController);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Menu Deliver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
