package controlerView;

import controlers.LoginController;
import controlers.OwnerAccountController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.UserView;

import java.io.IOException;

public class LoginViewWindowController {

    private LoginController loginController;
    private OwnerAccountController ownerAccountController;

    public LoginViewWindowController(LoginController loginController, OwnerAccountController ownerAccountController) {
        this.loginController = loginController;
        this.ownerAccountController = ownerAccountController;

    }

    public PasswordField txtPassword;
    public TextField txtUsername;
    public Button btnLogIn;
    public Label txtInfoMesague;

    public void logIn(ActionEvent actionEvent) throws IOException {

        boolean loginAnswer = loginController.login(txtUsername.getText(), txtPassword.getText());

        if (loginAnswer) {
            txtInfoMesague.setText("Bienvenido");
            switch (loginController.getOwnerLogged().getRol()) {
                case "USER":
                    UserView userView = new UserView(ownerAccountController, loginController);
                    userView.start(new Stage());
                    //EmployeeView employeeView = new EmployeeView(userAcountControler, loginControler, gymControler);
                    //employeeView.start(new Stage());
                    break;
                case "DELIVER":
                    System.out.println("ES DELIVER");
                    System.out.println(loginController.getOwnerLogged().toString());
                    //AdminView adminView = new AdminView(userAcountControler, loginControler, gymControler);
                    //adminView.start(new Stage());
                    break;
            }

        } else {
            txtInfoMesague.setText("Usuario o contraseña incorrectos");
        }



    }



}
