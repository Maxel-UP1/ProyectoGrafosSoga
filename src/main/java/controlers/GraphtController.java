package controlers;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.YenKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.GraphPath;
import utilities.JacksonStorageUtilities;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class GraphtController {
    private Graph<Long, DefaultWeightedEdge> graph;
    private JacksonStorageUtilities jacksonStorageUtilities;
    private static final long OSMIDSTART = 956058028L;

    public GraphtController() {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public void setJacksonStorageUtilities(JacksonStorageUtilities jacksonStorageUtilities) {
        this.jacksonStorageUtilities = jacksonStorageUtilities;
    }

    public void addVertex(long id) {
        graph.addVertex(id);
    }

    public void addEdge(long u, long v, double weight) {
        graph.addEdge(u, v);
        graph.setEdgeWeight(graph.getEdge(u, v), weight);
    }

    public void fastestRoutes(long target, String nameOutputFile) {
        try {
            List<GraphPath<Long, DefaultWeightedEdge>> paths = findShortestPaths(target, 3);

            if (!paths.isEmpty()) {
                List<Long> shortestPath = paths.get(0).getVertexList(); // ruta más óptima
                List<Long> secondShortestPath = paths.size() > 1 ? paths.get(1).getVertexList() : null;
                List<Long> thirdShortestPath = paths.size() > 2 ? paths.get(2).getVertexList() : null;

                jacksonStorageUtilities.savePathsAsGeoJson(shortestPath, secondShortestPath, thirdShortestPath, nameOutputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRute() {
        try {
            URI uri = new URI("http://localhost:63342/ProyectoGrafosSoga/src/main/java/persistence/map.html?_ijt=qh45e7u86t73n1s12n0e0iibl7&_ij_reload=RELOAD_ON_SAVE");
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
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
    }

    private List<GraphPath<Long, DefaultWeightedEdge>> findShortestPaths(long target, int i) {
        YenKShortestPath<Long, DefaultWeightedEdge> yenKShortestPath = new YenKShortestPath<>(graph);
        return yenKShortestPath.getPaths(OSMIDSTART, target, i);
    }
}


