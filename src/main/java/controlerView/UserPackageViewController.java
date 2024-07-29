package controlerView;
import model.Package;
import controlers.LoginController;
import controlers.UserAccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class UserPackageViewController {
    public TableView<Package> tablePackages;
    public TableColumn<Package, String> colPackageId;
    public TableColumn<Package, String> colName;
    public TableColumn<Package, String> colAddress;
    public TableColumn<Package, String> colStatus;
    public Button btnBack;
    private UserAccountController userAccountController;
    private LoginController loginController;
    private ObservableList<Package> packagesList;

    // Mapa de direcciones
    private static final Map<String, String> addressMap = new HashMap<String, String>() {{
        put("U.P.T.C", "7781524482");
        put("Terminal de Buses Sogamoso", "4084049672");
        put("Parque Del Norte", "7787663580");
        put("Colegio Sugamuxi", "1016196371");
        put("Personalizado", "Personalizado");
        put("El Cerrito", "1016192271");
        put("Terminal De Buses", "4084049672");
    }};

    public UserPackageViewController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
        this.packagesList = FXCollections.observableArrayList(userAccountController.getPackByUser(loginController.getAccountLogged()));
    }

    public void initialize() {
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Configurar la celda personalizada para la columna de direcciones
        colAddress.setCellFactory(column -> new TableCell<Package, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Buscar en el mapa si hay un nombre para esta dirección
                    String addressName = addressMap.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().equals(item))
                            .map(Map.Entry::getKey)
                            .findFirst()
                            .orElse(item); // Usar el OSMID si no se encuentra
                    setText(addressName);
                }
            }
        });

        tablePackages.setItems(packagesList);
    }

    public void goBack(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
