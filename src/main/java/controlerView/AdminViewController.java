package controlerView;

import controlers.LoginController;
import controlers.UserAccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import model.Package;
import view.TestGrapht;

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
    private TableColumn<Package, Void> colAction; // Nueva columna para el botón

    @FXML
    public void initialize() {
        System.out.println("Initializing TableView");
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Configuración de la columna de acción
        colAction.setCellFactory(new Callback<TableColumn<Package, Void>, TableCell<Package, Void>>() {
            @Override
            public TableCell<Package, Void> call(final TableColumn<Package, Void> param) {
                final TableCell<Package, Void> cell = new TableCell<Package, Void>() {

                    private final Button btn = new Button("Ver Ruta");

                    {
                        btn.setOnAction((event) -> {
                            Package data = getTableView().getItems().get(getIndex());
                            System.out.println("Ver Ruta del Paquete: " + data.getId());
                            // Aquí puedes añadir la lógica para ver la ruta
                            TestGrapht testGrapht = new TestGrapht();

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        });

        ObservableList<Package> packages = FXCollections.observableArrayList(packagesList);
        tablePackages.setItems(packages);
        System.out.println("TableView initialized with packages: " + packages.size());

        // Debug output to see if data is set correctly
        for (Package pack : tablePackages.getItems()) {
            System.out.println("TableView Item: " + pack.getId() + " " + pack.getName() + " " + pack.getAddress() + " " + pack.getStatus());
        }
    }
}
