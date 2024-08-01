import java.util.List;
import java.util.ArrayList;

public class shortestPathImplementation implements shortestPathInterface {

    private GraphADT<String, Double> graph;
    private List<String> route;
    private double totalMiles;

    // Constructor to set the values
    public shortestPathImplementation(GraphADT<String, Double> graph, List<String> route, double totalMiles) {
        this.route = route;
	this.graph = graph;
        this.totalMiles = totalMiles;
    }

    @Override
    public List<String> getRoute() {
        return route;
    }

    @Override
    public List<Integer> findMilesPerSegment(){
	return calculateMilesPerSegment(route, graph);
    }



    @Override
    public double getTotalMiles(){
	return totalMiles;
    }


   private List<Integer> calculateMilesPerSegment(List<String> route, GraphADT<String, Double> graph) {
    List<Integer> milesPerSegment = new ArrayList<>();

    // Iterate through the nodes in the path
    for (int i = 0; i < route.size() - 1; i++) {
        String startNode = route.get(i);
        String endNode = route.get(i + 1);

        // Get the edge weight between consecutive nodes
        Double edgeWeight = graph.getEdge(startNode, endNode);

        // Assuming edgeWeight is in miles and is not null
        if (edgeWeight != null) {
            milesPerSegment.add(edgeWeight.intValue());
        } else {
            // Handle the case where there is no edge between startNode and endNode
            // You might want to throw an exception or handle it according to your requirements
            throw new IllegalStateException("No edge found between " + startNode + " and " + endNode);
        }
      }
	 return milesPerSegment;

   }



}
