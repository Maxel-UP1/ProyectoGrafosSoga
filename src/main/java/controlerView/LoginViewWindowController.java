package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import view.AdminView;
import view.UserView;

import java.io.IOException;

public class LoginViewWindowController {

    private LoginController loginController;
    private UserAccountController userAccountController;

    public LoginViewWindowController(LoginController loginController, UserAccountController userAccountController) {
        this.loginController = loginController;
        this.userAccountController = userAccountController;

    }

    public PasswordField txtPassword;
    public TextField txtUsername;
    public Button btnLogIn;
    public Label txtInfoMesague;

    public void logIn(ActionEvent actionEvent) throws IOException {

        boolean loginAnswer = loginController.login(txtUsername.getText(), txtPassword.getText());

        if (loginAnswer) {
            txtInfoMesague.setText("Bienvenido");

            switch (loginController.getAccountLogged().getRol().name()) {
                case "USER":
                    UserView userView = new UserView(userAccountController, loginController);
                    userView.start(new Stage());
                    break;
                case "DELIVER":
                    AdminView adminView = new AdminView(userAccountController, loginController);
                    adminView.start(new Stage());
                    System.out.println("ES DELIVER");
                    System.out.println(loginController.getAccountLogged().toString());
                    //AdminView adminView = new AdminView(userAcountControler, loginControler, gymControler);
                    //adminView.start(new Stage());
                    break;
            }

        } else {
            txtInfoMesague.setText("Usuario o contraseña incorrectos");
        }



    }



}
