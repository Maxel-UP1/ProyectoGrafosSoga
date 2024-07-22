package view;
import controlerView.LoginViewWindowController;
import controlers.LoginController;
import controlers.UserAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginView extends Application {
    private  UserAccountController userAccountController;
    public static Stage currentStage;

    public LoginView(){
        userAccountController = new UserAccountController();
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Leer la persistencia de usuarios
        userAccountController.chargeUsersReadFile("users");
        userAccountController.chargePackagesReadFile("packages");
        LoginController loginController = new LoginController(userAccountController);




        //muestra la ventana principal
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("loginView.fxml"));
        LoginViewWindowController loginControllerView = new LoginViewWindowController(loginController, userAccountController);
        fxmlLoader.setControllerFactory(controllerClass -> {
            // Devolver la instancia del controlador
            return loginControllerView;
        });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Login Paquetería UPTC");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
