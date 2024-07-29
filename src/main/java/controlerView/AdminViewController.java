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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminViewController {
    public Button btnUpdatePack;
    public Label lblNameDeliver;
    private ArrayList<Package> packagesList;
    private LoginController loginController;
    private UserAccountController userAccountController;
    private JacksonStorageUtilities jacksonStorageUtilities;
    private GraphtController graphtController;
    int cont = 0;

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

    public AdminViewController(UserAccountController userAccountController, LoginController loginController) throws IOException {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
        this.jacksonStorageUtilities = new JacksonStorageUtilities();
        jacksonStorageUtilities.buildGraph();
        this.graphtController = jacksonStorageUtilities.getGraphtController();
        this.packagesList = userAccountController.getPackByUser(loginController.getAccountLogged());
    }

    @FXML
    public void initialize() {

        colPackageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Configuración de la celda personalizada para la columna de direcciones
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

        // Configuración de la columna de estado con ComboBox
        colStatus.setCellFactory(col -> new TableCell<>() {
            private final ComboBox<String> comboBox = new ComboBox<>();

            {
                comboBox.getItems().addAll("pendiente", "encamino", "entregado", "devuelto");
                comboBox.setOnAction(e -> {
                    Package pack = getTableView().getItems().get(getIndex());
                    pack.setStatus(comboBox.getValue());
                   cont ++;
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
                            // Muestra la ruta del paquete
                            graphtController.fastestRoutes(Long.parseLong(data.getAddress()), "shortpath");
                            try {
                                graphtController.openRute();
                            } catch (URISyntaxException | IOException e) {
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
        if (cont > 2) {
            userAccountController.actualizarEstadoPack(packagesList);
        }

    }

    public void showNameUserLoged() {
        lblNameDeliver.setText(userAccountController.userById(loginController.getAccountLogged().getIdUser()).getName());
    }

    @FXML
    public void bottonEstadoPack(ActionEvent actionEvent) throws IOException {
        userAccountController.actualizarEstadoPack(packagesList);
    }
}
