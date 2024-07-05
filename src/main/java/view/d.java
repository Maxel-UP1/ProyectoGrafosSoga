package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class d extends Application {

    private static final double MAP_WIDTH = 800;
    private static final double MAP_HEIGHT = 600;
    private static List<Long> shortestPath;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Mapa de Sogamoso - Ruta Más Corta");

        Pane root = new Pane();
        Canvas canvas = new Canvas(MAP_WIDTH, MAP_HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, MAP_WIDTH, MAP_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            // Cargar nodos y la ruta más corta desde el archivo GeoJSON
            JsonNode nodes = loadGeoJson("src/main/java/persistence/nodes.geojson");
            shortestPath = loadShortestPath("src/main/java/persistence/shortest_path.geojson");

            // Dibujar nodos y aristas en el canvas
            drawMap(canvas.getGraphicsContext2D(), nodes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawMap(GraphicsContext gc, JsonNode nodes) {
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);

        // Encontrar límites para escalar las coordenadas
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = Double.MIN_VALUE, maxY = Double.MIN_VALUE;
        for (JsonNode node : nodes.get("features")) {
            double x = node.get("geometry").get("coordinates").get(0).asDouble();
            double y = node.get("geometry").get("coordinates").get(1).asDouble();
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }

        double scaleX = MAP_WIDTH / (maxX - minX);
        double scaleY = MAP_HEIGHT / (maxY - minY);

        for (JsonNode node : nodes.get("features")) {
            double x = node.get("geometry").get("coordinates").get(0).asDouble();
            double y = node.get("geometry").get("coordinates").get(1).asDouble();
            double canvasX = (x - minX) * scaleX;
            double canvasY = MAP_HEIGHT - (y - minY) * scaleY; // Invertir la coordenada y para que el norte esté arriba
            gc.fillOval(canvasX, canvasY, 5, 5);
        }

        // Dibujar la ruta más corta
        gc.setStroke(Color.RED);
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            long u = shortestPath.get(i);
            long v = shortestPath.get(i + 1);
            drawEdge(gc, nodes, u, v, minX, minY, scaleX, scaleY);
        }
    }

    private void drawEdge(GraphicsContext gc, JsonNode nodes, long u, long v, double minX, double minY, double scaleX, double scaleY) {
        double ux = 0, uy = 0, vx = 0, vy = 0;
        for (JsonNode node : nodes.get("features")) {
            if (node.get("properties").get("osmid").asLong() == u) {
                ux = node.get("geometry").get("coordinates").get(0).asDouble();
                uy = node.get("geometry").get("coordinates").get(1).asDouble();
            }
            if (node.get("properties").get("osmid").asLong() == v) {
                vx = node.get("geometry").get("coordinates").get(0).asDouble();
                vy = node.get("geometry").get("coordinates").get(1).asDouble();
            }
        }
        double canvasUx = (ux - minX) * scaleX;
        double canvasUy = MAP_HEIGHT - (uy - minY) * scaleY;
        double canvasVx = (vx - minX) * scaleX;
        double canvasVy = MAP_HEIGHT - (vy - minY) * scaleY;
        gc.strokeLine(canvasUx, canvasUy, canvasVx, canvasVy);
    }

    private JsonNode loadGeoJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(filePath));
    }

    private List<Long> loadShortestPath(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(filePath));
        List<Long> path = new ArrayList<>();

        for (JsonNode feature : root.get("features")) {
            path.add(feature.get("properties").get("osmid").asLong());
        }
        return path;
    }
}
