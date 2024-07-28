package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Account;
import utilities.Utilities;
import javax.swing.JOptionPane;

public class UserViewWindowController {

    public Label lblInfoOrderPack;
    private LoginController loginController;
    private UserAccountController userAccountController;
    public String addressSelected;
    public TextField txtNamePack;
    public ComboBox<String> boxDeliveryAddres;
    public TextField txtIdCancelPack;
    public Label lblInfoCancell;
    public Label lblNameUser;
    public Button btnCreatePack;
    public Button brnSeePacks;
    public Button btnCancellPack;
    public TextField txtCustomAddress; // New TextField

    Utilities utilities = new Utilities();
    ObservableList<String> addresList = FXCollections.observableArrayList("U.P.T.C", "Colegio Sugamuxi",
            "Parque Del Norte", "Personalizado");
    JOptionPane jp = new JOptionPane();

    public UserViewWindowController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
    }

    public void addPack(ActionEvent actionEvent) {
        String namePack = txtNamePack.getText();
        Account owner = loginController.getAccountLogged();
        Account deliveryMan = userAccountController.firtsDeliveredMan();

        if (namePack.isEmpty()) {
            lblInfoOrderPack.setText("El nombre del paquete no puede estar vacío");
        } else if (addressSelected.equals("")) {
            lblInfoOrderPack.setText("Debe seleccionar una dirección");
        } else if (addressSelected.equals("Personalizado") && txtCustomAddress.getText().isEmpty()) {
            lblInfoOrderPack.setText("Debe ingresar una dirección personalizada");
        } else {
            String finalAddress = addressSelected.equals("Personalizado") ? txtCustomAddress.getText() : addressSelected;
            userAccountController.addPack(owner, deliveryMan, namePack, finalAddress, "Pendiente");
            lblInfoOrderPack.setText("Paquete creado con éxito");
            txtNamePack.setText("");
            txtCustomAddress.setText("");
            txtCustomAddress.setVisible(false);
        }
    }

    public void seePacks(ActionEvent actionEvent) {
    }

    public void cancelPack(ActionEvent actionEvent) {
        String idPack = txtIdCancelPack.getText();
        if (idPack.isEmpty()) {
            lblInfoCancell.setText("El ID del paquete no puede estar vacío");
        } else {
            userAccountController.cancelPack(idPack);
            lblInfoCancell.setText("Paquete cancelado con éxito");
        }
    }

    public void viewInfoID(MouseEvent mouseEvent) {
        lblInfoCancell.setText("Si no conoce el ID, seleccione Ver envíos Realizados");
    }

    public void selecAdrres(ActionEvent actionEvent) {
        addressSelected = boxDeliveryAddres.getValue();
        if ("Personalizado".equals(addressSelected)) {
            txtCustomAddress.setVisible(true);
        } else {
            txtCustomAddress.setVisible(false);
        }
    }

    public void viewAdrees(Event event) {
        utilities.fillComboVox(boxDeliveryAddres, addresList);
    }
}
