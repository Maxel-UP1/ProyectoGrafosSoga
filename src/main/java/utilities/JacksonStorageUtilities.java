package utilities;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controlers.GraphtController;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonStorageUtilities {

    private GraphtController graphtController;
    private JsonNode nodes;
    private JsonNode edges;

    private static final String FILEPATH = "src/main/java/persistence/";
    private static final String EXTENSION = ".geojson";

    public GraphtController getGraphtController() {
        return graphtController;
    }

    public JacksonStorageUtilities() {
        graphtController = new GraphtController();
        graphtController.setJacksonStorageUtilities(this);
    }

    private static JsonNode loadGeoJson(String filePath) throws IOException {
        return JacksonStorageUtilities.ReadJGeojson(filePath);
    }

    public void loadGraphData() throws IOException {
        nodes = loadGeoJson(FILEPATH + "nodes" + EXTENSION);
        edges = loadGeoJson(FILEPATH + "edges" + EXTENSION);
    }

    public void buildGraph() throws IOException {
        loadGraphData();

        // Añadir vértices desde la propiedad osmid
        for (JsonNode node : nodes.get("features")) {
            long id = node.get("properties").get("osmid").asLong();
            graphtController.addVertex(id);
        }

        // Añadir aristas
        for (JsonNode edge : edges.get("features")) {
            long u = edge.get("properties").get("u").asLong();
            long v = edge.get("properties").get("v").asLong();
            if (u != v) { // Verificar que no sea un bucle
                double weight = edge.get("properties").get("length").asDouble();
                graphtController.addEdge(u, v, weight);
            }
        }
    }

    public static JsonNode ReadJGeojson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(filePath));
    }

    public void savePathsAsGeoJson(List<Long> shortestPath, List<Long> secondShortestPath, List<Long> thirdShortestPath, String nameOutputFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("type", "FeatureCollection");
        ArrayNode features = mapper.createArrayNode();

        // Añadir los nodos y aristas de la ruta más corta
        addPathToFeatures(shortestPath, features, "shortest");

        // Añadir la segunda ruta más corta si existe
        if (secondShortestPath != null) {
            addPathToFeatures(secondShortestPath, features, "secondShortest");
        }

        // Añadir la tercera ruta más corta si existe
        if (thirdShortestPath != null) {
            addPathToFeatures(thirdShortestPath, features, "thirdShortest");
        }

        root.set("features", features);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILEPATH + nameOutputFile + EXTENSION), root);
    }

    private void addPathToFeatures(List<Long> path, ArrayNode features, String pathType) {
        // Añadir los nodos de la ruta
        for (long id : path) {
            for (JsonNode node : nodes.get("features")) {
                if (node.get("properties").get("osmid").asLong() == id) {
                    ((ObjectNode) node.get("properties")).put("pathType", pathType);
                    features.add(node);
                }
            }
        }

        // Añadir las aristas de la ruta
        for (int i = 0; i < path.size() - 1; i++) {
            long u = path.get(i);
            long v = path.get(i + 1);
            for (JsonNode edge : edges.get("features")) {
                if ((edge.get("properties").get("u").asLong() == u && edge.get("properties").get("v").asLong() == v) ||
                        (edge.get("properties").get("u").asLong() == v && edge.get("properties").get("v").asLong() == u)) {
                    ((ObjectNode) edge.get("properties")).put("pathType", pathType);
                    features.add(edge);
                }
            }
        }
    }
}
