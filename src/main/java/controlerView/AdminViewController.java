package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Package;

import java.util.ArrayList;

public class AdminViewController {
    private ArrayList<Package> packagesList;
    private LoginController loginController;
    private UserAccountController userAccountController;

    public AdminViewController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
        this.packagesList = userAccountController.getPackagesList();
        System.out.println("Packages list loaded: " + packagesList.size());
        for (Package pack : packagesList) {
            System.out.println(pack.getId() + " " + pack.getName() + " " + pack.getAddress() + " " + pack.getStatus());
        }
    }

    @FXML
    private TableView<Package> tablePackages;

    @FXML
    private TableColumn<Package, String> colPackageId;

    @FXML
    private TableColumn<Package, String> colName;

    @FXML
    private TableColumn<Package, String> colAddress;

    @FXML
    private TableColumn<Package, String> colStatus;

    @FXML
    public void initialize() {
        System.out.println("Initializing TableView");
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<Package> packages = FXCollections.observableArrayList(packagesList);
        tablePackages.setItems(packages);
        System.out.println("TableView initialized with packages: " + packages.size());

        // Debug output to see if data is set correctly
        for (Package pack : tablePackages.getItems()) {
            System.out.println("TableView Item: " + pack.getId() + " " + pack.getName() + " " + pack.getAddress() + " " + pack.getStatus());
        }
    }
}
