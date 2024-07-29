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
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

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
    public TextField txtCustomCoordinates; // TextField for custom coordinates

    Utilities utilities = new Utilities();
    ObservableList<String> addresList = FXCollections.observableArrayList(
            "U.P.T.C", "Colegio Sugamuxi", "Parque Del Norte",
            "El Cerrito", "Terminal De Buses", "Personalizado"
    );
    JOptionPane jp = new JOptionPane();

    // Mapear direcciones a sus respectivos OSMIDs
    HashMap<String, String> addressMap = new HashMap<String, String>() {{
        put("U.P.T.C", "7781524482");
        put("Terminal de Buses Sogamoso", "4084049672");
        put("Parque Del Norte", "7787663580");
        put("Colegio Sugamuxi", "1016196371");
        put("Personalizado", "Personalizado");
        put("El Cerrito", "1016192271");
        put("Terminal De Buses", "4084049672");
    }};

    // Conjunto para almacenar OSMIDs desde el archivo de nodos
    Set<String> validOsmids = new HashSet<>();
    JsonNode nodes;

    public UserViewWindowController(UserAccountController userAccountController, LoginController loginController) {
        this.userAccountController = userAccountController;
        this.loginController = loginController;
        loadValidOsmids();
    }

    private void loadValidOsmids() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            nodes = mapper.readTree(new File("src/main/java/persistence/nodes.geojson"));
            JsonNode features = nodes.get("features");

            if (features.isArray()) {
                for (JsonNode feature : features) {
                    JsonNode properties = feature.get("properties");
                    String osmid = properties.get("osmid").asText();
                    validOsmids.add(osmid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String findClosestOsmid(double lat, double lon) {
        double minDistance = Double.MAX_VALUE;
        String closestOsmid = null;

        if (nodes != null) {
            JsonNode features = nodes.get("features");
            if (features.isArray()) {
                for (JsonNode feature : features) {
                    JsonNode properties = feature.get("properties");
                    JsonNode geometry = feature.get("geometry");
                    ArrayNode coordinates = (ArrayNode) geometry.get("coordinates");
                    double nodeLon = coordinates.get(0).asDouble();
                    double nodeLat = coordinates.get(1).asDouble();
                    double distance = haversine(lat, lon, nodeLat, nodeLon);

                    if (distance < minDistance) {
                        minDistance = distance;
                        closestOsmid = properties.get("osmid").asText();
                    }
                }
            }
        }

        return closestOsmid;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radio de la Tierra en km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // Convertir a metros
    }

    public void addPack(ActionEvent actionEvent) {
        String namePack = txtNamePack.getText();
        Account owner = loginController.getAccountLogged();
        Account deliveryMan = userAccountController.firtsDeliveredMan();
        String finalAddress = null;

        if (namePack.isEmpty()) {
            lblInfoOrderPack.setText("El nombre del paquete no puede estar vacío");
            return;
        }

        if (addressSelected == null) {
            lblInfoOrderPack.setText("Debe seleccionar una dirección");
            return;
        }

        if (addressSelected.equals("Personalizado")) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(txtCustomCoordinates.getText());
                JsonNode coordinates = json.get("features").get(0).get("geometry").get("coordinates");
                double lon = coordinates.get(0).asDouble();
                double lat = coordinates.get(1).asDouble();
                finalAddress = findClosestOsmid(lat, lon);
                if (finalAddress == null) {
                    lblInfoOrderPack.setText("No se encontró un OSMID cercano para las coordenadas proporcionadas");
                    return;
                }
            } catch (Exception e) {
                lblInfoOrderPack.setText("Formato de coordenadas inválido");
                return;
            }
        } else {
            finalAddress = addressSelected;
        }

        userAccountController.addPack(owner, deliveryMan, namePack, finalAddress, "Pendiente");
        lblInfoOrderPack.setText("Paquete creado con éxito");
        txtNamePack.setText("");
        txtCustomCoordinates.setText("");
        txtCustomCoordinates.setVisible(false);
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

    public void selecAdrres(ActionEvent actionEvent) throws IOException, URISyntaxException {
        String selectedAddress = boxDeliveryAddres.getValue();
        addressSelected = addressMap.get(selectedAddress);
        if ("Personalizado".equals(selectedAddress)) {
            txtCustomCoordinates.setVisible(true);
            URI uri = new URI("https://geojson.io/#new&map=13.31/5.71738/-72.93601");
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } else {
            txtCustomCoordinates.setVisible(false);
        }
    }

    public void viewAdrees(Event event) {
        utilities.fillComboVox(boxDeliveryAddres, addresList);
    }
}
