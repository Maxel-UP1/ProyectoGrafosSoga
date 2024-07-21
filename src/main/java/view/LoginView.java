package view;
import controlerView.LoginViewController;
import controlers.LoginController;
import controlers.OwnerAccountController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginView extends Application {
    private  OwnerAccountController ownerAccountController;
    public static Stage currentStage;


    @Override
    public void start(Stage stage) throws IOException {
        //Leer la persistencia de usuarios
        OwnerAccountController ownerAccountController = new OwnerAccountController();
        LoginController loginController = new LoginController(ownerAccountController);
        ownerAccountController.chargeOwnersReadFile("users");




        //muestra la ventana principal
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("loginView.fxml"));
        LoginViewController loginControllerView = new LoginViewController(loginController);
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
