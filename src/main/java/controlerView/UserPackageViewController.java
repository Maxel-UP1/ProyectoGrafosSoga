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

public class UserPackageViewController {
    public TableView<Package> tablePackages;
    public TableColumn<Package, String> colPackageId;
    public TableColumn<Package, String> colName;
    public TableColumn<Package, String> colAddress;
    public TableColumn<Package, String> colStatus;
    public Button btnBack;
    private UserAccountController userAccountController;
    private LoginController loginController;
    private ObservableList<Package> packagesList; // Añadido

    public UserPackageViewController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
        this.packagesList = FXCollections.observableArrayList(userAccountController.getPackByUser(loginController.getAccountLogged())); // Añadido
    }

    public void initialize() {
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<Package> packages = FXCollections.observableArrayList(packagesList);
        tablePackages.setItems(packages);
    }

    public void goBack(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
