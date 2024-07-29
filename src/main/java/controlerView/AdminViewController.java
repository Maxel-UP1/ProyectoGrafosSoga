package controlerView;

import controlers.GraphtController;
import controlers.LoginController;
import controlers.UserAccountController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Package;
import utilities.JacksonStorageUtilities;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class AdminViewController {
    public Button btnUpdatePack;
    public Label lblNameDeliver;
    private ArrayList<Package> packagesList;
    private LoginController loginController;
    private UserAccountController userAccountController;
    private JacksonStorageUtilities jacksonStorageUtilities;
    private GraphtController graphtController;

    public AdminViewController(UserAccountController userAccountController, LoginController loginController) throws IOException {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
        this.jacksonStorageUtilities = new JacksonStorageUtilities();
        jacksonStorageUtilities.buildGraph();
        this.graphtController = jacksonStorageUtilities.getGraphtController();
        this.packagesList = userAccountController.getPackByUser(loginController.getAccountLogged()); // Añadido

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
    private TableColumn<Package, Void> colAction;



    @FXML
    public void initialize() {
        colPackageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Configuración de la columna de estado con ComboBox
        colStatus.setCellFactory(col -> new TableCell<>() {
            private final ComboBox<String> comboBox = new ComboBox<>();

            {
                comboBox.getItems().addAll("pendiente", "encamino", "entregado", "devuelto");
                comboBox.setOnAction(e -> {
                    Package pack = getTableView().getItems().get(getIndex());
                    pack.setStatus(comboBox.getValue());
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Package pack = getTableView().getItems().get(getIndex());
                    comboBox.setValue(pack.getStatus());
                    setGraphic(comboBox);

                }
            }
        });

        // Configuración de la columna de acción con botón
        colAction.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Package, Void> call(final TableColumn<Package, Void> param) {
                final TableCell<Package, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Ver Ruta");

                    {
                        btn.setOnAction(event -> {
                            Package data = getTableView().getItems().get(getIndex());
                            //Muestra la ruta del paquete
                            graphtController.fastestRoutes(Long.parseLong(data.getAddress()), "shortpath");
                            try {
                                graphtController.openRute();
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
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
        ObservableList<Package> packag = FXCollections.observableArrayList(packagesList);
        tablePackages.setItems(packag);
        userAccountController.actualizarEstadoPack(packagesList);
    }

    public void showNameUserLoged() {
        lblNameDeliver.setText(userAccountController.userById(loginController.getAccountLogged().getIdUser()).getName());
    }
    
    @FXML
    public void bottonEstadoPack(ActionEvent actionEvent) throws IOException {
        userAccountController.actualizarEstadoPack(packagesList);
    }
}
