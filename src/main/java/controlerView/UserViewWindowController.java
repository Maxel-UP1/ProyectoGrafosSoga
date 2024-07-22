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
    public ComboBox boxDeliveryAddres;
    public TextField txtIdCancelPack;
    public Label lblInfoCancell;
    public Label lblNameUser;
    public Button btnCreatePack;
    public Button brnSeePacks;
    public Button btnCancellPack;

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
        //necesita un repartidor, por defecto es admin
        Account deliveryMan = userAccountController.firtsDeliveredMan();

        if (namePack.isEmpty()) {
            lblInfoOrderPack.setText("El nombre del paquete no puede estar vacío");
        } else if (addressSelected.isEmpty()) {
            lblInfoOrderPack.setText("Debe seleccionar una dirección");
        } else {
            //crea el paquete y lo persiste
            userAccountController.addPack(owner, deliveryMan, namePack, addressSelected, "Pendiente");
            lblInfoOrderPack.setText("Paquete creado con éxito");
        }
    }

    public void seePacks(ActionEvent actionEvent) {
    }

    public void cancelPack(ActionEvent actionEvent) {
    }

    // ver la sugerencia para conocer los ID
    public void viewInfoID(MouseEvent mouseEvent) {
        lblInfoCancell.setText("Si no conoce el ID, seleccione Ver envíos Realizados");
    }

    public void selecAdrres(ActionEvent actionEvent) {
        addressSelected = boxDeliveryAddres.getValue().toString();
    }

    // llenar el vbox
    public void viewAdrees(Event event) {
        utilities.fillComboVox(boxDeliveryAddres, addresList);
    }
}