package view;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestGrapht {


    public static void main(String[] args) {
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
                if (u != v) {  // Verificar que no sea un bucle
                    double weight = edge.get("properties").get("length").asDouble();
                    graph.addEdge(u, v);
                    graph.setEdgeWeight(graph.getEdge(u, v), weight);
                } else {
                    System.out.println("Arista bucle ignorada: " + u + " -> " + v);
                }
            }

            // Imprimir detalles del grafo
            System.out.println("Número de vértices: " + graph.vertexSet().size());
            System.out.println("Número de aristas: " + graph.edgeSet().size());
            System.out.println("Grafo creado con éxito!");

            // Encontrar la ruta más corta entre dos puntos
            //Dos vertices cualquiera desde la propiedad osmid
            long source = 1016190752L; // punto de origen
            long target = 7784867706L; // punto de destino

            DijkstraShortestPath<Long, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
            List<Long> path = dijkstraAlg.getPath(source, target).getVertexList();
            double pathWeight = dijkstraAlg.getPath(source, target).getWeight();

            System.out.println("Ruta más corta desde " + source + " hasta " + target + ": " + path);
            System.out.println("Peso total de la ruta: " + pathWeight);

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

}