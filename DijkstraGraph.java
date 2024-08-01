// --== CS400 File Header Information ==--
// Name: Rishika Gudise 
// Email: gudise@wisc.edu
// Group and Team: G13
// Group TA: ROBERT NAGEL
// Lecturer: Florian H.
// Notes to Grader: <optional extra notes>

import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import java.util.NoSuchElementException;
/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes. This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType, EdgeType>
        implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph. The final node in this path is stored in its node
     * field. The total cost of this path is stored in its cost field. And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;

        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }

        public int compareTo(SearchNode other) {
            if (cost > other.cost)
                return +1;
            if (cost < other.cost)
                return -1;
            return 0;
        }
    }

    /**
     * Constructor that sets the map that the graph uses.
     * @param map the map that the graph uses to map a data object to the node
     *        object it is stored in
     */
    public DijkstraGraph(MapADT<NodeType, Node> map) {
        super(map);
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations. The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *                                or when either start or end data do not
     *                                correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        // implement in step 5.3
        // Create a priority queue to greedily explore shorter path possibilities
        PriorityQueue<SearchNode> priorityQueue = new PriorityQueue<>();

	// Create a map to keep track of visited nodes and their associated path information
        MapADT<NodeType, SearchNode> visitedNodes = new PlaceholderMap<>();

	// Create the starting SearchNode
        SearchNode startNode = new SearchNode(nodes.get(start), 0.0, null);
        priorityQueue.add(startNode);

	while (!priorityQueue.isEmpty()) {
            // Get the node with the shortest known path
            SearchNode currentNode = priorityQueue.poll();

            // If the destination node is reached, return the SearchNode
            if (currentNode.node.data.equals(end)) {
                return currentNode;
            }

	   // Skip this node if it has already been visited
            if (visitedNodes.containsKey(currentNode.node.data)) {
                continue;
            }

            // Mark the node as visited
            visitedNodes.put(currentNode.node.data, currentNode);

	 for (Edge edge : currentNode.node.edgesLeaving) {
                Node successor = edge.successor;
                double newCost = currentNode.cost + edge.data.doubleValue();
                SearchNode successorNode = new SearchNode(successor, newCost, currentNode);

                if (!visitedNodes.containsKey(successor.data)) {
                    priorityQueue.add(successorNode);
                }
            }
        }

	// If the loop completes without finding a path, throw NoSuchElementException
        throw new NoSuchElementException("No path from " + start.toString() + " to " +
                end.toString());
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value. This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path. This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {
        SearchNode endNode = computeShortestPath(start, end);

        // Reconstruct the path from end to start
        List<NodeType> pathData = new LinkedList<>();
        SearchNode currentNode = endNode;
        while (currentNode != null) {
            pathData.add(0, currentNode.node.data); // Add at the beginning to reverse the order
            currentNode = currentNode.predecessor;
        }

        return pathData;
	}

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data. This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
        // implement in step 5.4
        SearchNode endNode = computeShortestPath(start, end);
        return endNode.cost;
    }

    // TODO: implement 3+ tests in step 4.1

	/**
        *checks to see if the cost and sequences of the path between 2 nodes is returned as expected (2)
        */
	@Test
	public void Test1(){
		//initialize the graph
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
		DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

		//add nodes and edges
		dijkstraGraph.insertNode("A");
		dijkstraGraph.insertNode("B");
		dijkstraGraph.insertNode("C");
        	dijkstraGraph.insertEdge("A", "B", 1.0);
        	dijkstraGraph.insertEdge("B", "C", 2.0);
        	dijkstraGraph.insertEdge("A", "C", 4.0);

		List<String> pathData = dijkstraGraph.shortestPathData("A", "C");
        	double pathCost = dijkstraGraph.shortestPathCost("A", "C");

		List<String> expectedPathData = List.of("A", "B", "C");
        	double expectedPathCost = 3.0; // The sum of edge weights along the shortest path

        	// Assert that the actual results match the expected results
        	assertIterableEquals(expectedPathData, pathData);
        	assertEquals(expectedPathCost, pathCost);

	}

	/**
	*checks to see if the cost and sequences of the path between 2 nodes is returned as expected (2)
	*/

	@Test
	public void Test2(){
		//initialize the graph
                MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

                //add nodes and edges
                dijkstraGraph.insertNode("A");
                dijkstraGraph.insertNode("B");
                dijkstraGraph.insertNode("C");
                dijkstraGraph.insertEdge("A", "B", 1.0);
                dijkstraGraph.insertEdge("B", "C", 2.0);
                dijkstraGraph.insertEdge("A", "C", 4.0);

                List<String> pathData = dijkstraGraph.shortestPathData("B", "C");
                double pathCost = dijkstraGraph.shortestPathCost("B", "C");

		List<String> expectedPathData = List.of("B", "C");
        	double expectedPathCost = 2.0;

		assertIterableEquals(expectedPathData, pathData);
                assertEquals(expectedPathCost, pathCost);

	}


	/**
         * Check that a NoSuchElementException is thrown if start node does not exist
         * in graph.
         */

	@Test
	public void Test3(){
		//initialize the graph 
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

		//add nodes and edges 
		dijkstraGraph.insertNode("A");
                dijkstraGraph.insertNode("B");
                dijkstraGraph.insertNode("C");
		dijkstraGraph.insertNode("D");
                dijkstraGraph.insertNode("E");
                dijkstraGraph.insertEdge("A", "B", 1.0);
                dijkstraGraph.insertEdge("B", "C", 2.0);
                dijkstraGraph.insertEdge("A", "C", 4.0);

		//assert if nosuchelementexception is thrown when trying to find shortest path between 2 disconnected nodes.
		Assertions.assertThrows(NoSuchElementException.class,  () -> dijkstraGraph.computeShortestPath("A", "E"));


	}


}

