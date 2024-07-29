package view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.YenKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.GraphPath;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class TestGrapht {

    public TestGrapht(String direccion) {

        try {
            // Cargar los datos
            JsonNode nodes = loadGeoJson("src/main/java/persistence/nodes.geojson");
            JsonNode edges = loadGeoJson("src/main/java/persistence/edges.geojson");

            // Crear el grafo
            Graph<Long, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

            // Añadir vértices desde la propiedad osmid
            for (JsonNode node : nodes.get("features")) {
                long id = node.get("properties").get("osmid").asLong();
                graph.addVertex(id);
            }

            // Añadir aristas
            for (JsonNode edge : edges.get("features")) {
                long u = edge.get("properties").get("u").asLong();
                long v = edge.get("properties").get("v").asLong();
                if (u != v) { // Verificar que no sea un bucle
                    double weight = edge.get("properties").get("length").asDouble();
                    graph.addEdge(u, v);
                    graph.setEdgeWeight(graph.getEdge(u, v), weight);
                } else {
                    System.out.println("Arista bucle ignorada: " + u + " -> " + v);
                }
            }

            // Encontrar las tres rutas más óptimas entre dos puntos
            //entrada de la uptc bodega
            long source = 956058028 ; // punto de origen
            long target = Long.parseLong(direccion); // punto de destino

            YenKShortestPath<Long, DefaultWeightedEdge> yenKShortestPath = new YenKShortestPath<>(graph);
            List<GraphPath<Long, DefaultWeightedEdge>> paths = yenKShortestPath.getPaths(source, target, 3);

            if (paths.size() > 0) {
                List<Long> shortestPath = paths.get(0).getVertexList(); //ruta mas optimaaaaaa
                //verificacion de rutas secundarias
                List<Long> secondShortestPath = paths.size() > 1 ? paths.get(1).getVertexList() : null;
                List<Long> thirdShortestPath = paths.size() > 2 ? paths.get(2).getVertexList() : null;

                // Guardar las rutas en un archivo GeoJSON
                savePathsAsGeoJson(shortestPath, secondShortestPath, thirdShortestPath, nodes, edges, "src/main/java/persistence/paths.geojson");
            } else {
                System.out.println("No se encontraron rutas entre los puntos seleccionados.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("No se pudo encontrar una ruta entre los puntos seleccionados.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static JsonNode loadGeoJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(new File(filePath));
    }

    private static void savePathsAsGeoJson(List<Long> shortestPath, List<Long> secondShortestPath, List<Long> thirdShortestPath, JsonNode nodes, JsonNode edges, String outputPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("type", "FeatureCollection");
        ArrayNode features = mapper.createArrayNode();

        // Añadir los nodos y aristas de la ruta más corta
        addPathToFeatures(shortestPath, nodes, edges, features, "shortest");

        // Añadir la segunda ruta más corta si existe
        if (secondShortestPath != null) {
            addPathToFeatures(secondShortestPath, nodes, edges, features, "secondShortest");
        }

        // Añadir la tercera ruta más corta si existe
        if (thirdShortestPath != null) {
            addPathToFeatures(thirdShortestPath, nodes, edges, features, "thirdShortest");
        }

        root.set("features", features);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), root);
    }

    private static void addPathToFeatures(List<Long> path, JsonNode nodes, JsonNode edges, ArrayNode features, String pathType) {
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
        //abre en el navegador
        OpenHTML openHTML = new OpenHTML();
    }


    }
class   OpenHTML {
public OpenHTML() {
    try {
        // Define la URL que quieres abrir
        URI uri = new URI("http://localhost:63342/ProyectoGrafosSoga/src/main/java/persistence/map.html?_ijt=qh45e7u86t73n1s12n0e0iibl7&_ij_reload=RELOAD_ON_SAVE");

        // Comprueba si Desktop es soportado en el sistema actual
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            // Comprueba si el navegador es soportado
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            } else {
                System.err.println("El navegador no es soportado.");
            }
        } else {
            System.err.println("Desktop no es soportado en este sistema.");
        }
    } catch (IOException | URISyntaxException e) {
        e.printStackTrace();
    }
}}



