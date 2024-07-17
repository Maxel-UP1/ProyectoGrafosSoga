package controlerView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExAplicatioController {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<?> packageTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> locationColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    void searchPackage(ActionEvent event) {
        // Implementar la lógica para buscar paquetes
        System.out.println("Buscar paquete: " + searchField.getText());
    }

    @FXML
    void addPackage(ActionEvent event) {
        // Implementar la lógica para agregar paquetes
        System.out.println("Agregar paquete");
    }

    @FXML
    void editPackage(ActionEvent event) {
        // Implementar la lógica para editar paquetes
        System.out.println("Editar paquete");
    }

    @FXML
    void deletePackage(ActionEvent event) {
        // Implementar la lógica para eliminar paquetes
        System.out.println("Eliminar paquete");
    }
}
