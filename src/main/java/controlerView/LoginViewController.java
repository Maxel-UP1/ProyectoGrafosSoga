package controlerView;

import controlers.LoginController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {

    private LoginController loginController;

    public LoginViewController(LoginController loginController) {
        this.loginController = loginController;

    }

    public PasswordField txtPassword;
    public TextField txtUsername;
    public Button btnLogIn;
    public Label txtInfoMesague;

    public void logIn(ActionEvent actionEvent) {

        boolean loginAnswer = loginController.login(txtUsername.getText(), txtPassword.getText());

        if (loginAnswer) {
            txtInfoMesague.setText("Bienvenido");
            switch (loginController.getOwnerLogged().getRol()) {
                case "USER":
                    System.out.println("ES USER ");
                    System.out.println(loginController.getOwnerLogged().toString());
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
