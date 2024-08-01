import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.*;
import java.util.Scanner;


public class BackendImplementation implements BackendInterface {

	private GraphADT<String, Double> graph;
	public BackendImplementation(GraphADT<String, Double> graph){
		this.graph = graph;
	}

	double totalMiles = 0.0;

	@Override
	public boolean readDataFromFile(String filePath) {
    		BufferedReader reader = null;

    		try {
        		reader = new BufferedReader(new FileReader(filePath));
        		String line;
        		while ((line = reader.readLine()) != null) {
            			if (line.contains("[") && line.contains("--")) {
                			String[] parts = line.split("--");
                			String startNode = parts[0].trim().replace("\"", "");
                			String endNode = parts[1].substring(0, parts[1].indexOf(" [")).trim().replace("\"", "");
               				double miles = Double.parseDouble(line.split("=")[1].replace("];", "").trim());
					totalMiles += miles;
                			graph.insertNode(startNode);
                			graph.insertNode(endNode);
                			graph.insertEdge(startNode, endNode, miles);
            		}
        	}
        	return true;
    		} catch (IOException e) {
        		e.printStackTrace();
			throw new IllegalArgumentException("Error reading file: " + e.getMessage());
        		//return false;
        	}
	}


	@Override
	public shortestPathImplementation findShortestPath(String start, String end){
		List<String> shortestPath  = graph.shortestPathData(start, end);
		double pathCost = graph.shortestPathCost(start, end);

		shortestPathImplementation result = new shortestPathImplementation(graph, shortestPath, pathCost);
		return result;
	}


	@Override
	public String getSummary(){
		int totalNodes = graph.getNodeCount();
		int totalEdges = graph.getEdgeCount();

		String summary = "Dataset Summary:\n";
    		summary += "Total Nodes: " + totalNodes + "\n";
    		summary += "Total Edges: " + totalEdges + "\n";
    		summary += "Total Miles: " + totalMiles + "\n";

		return summary;
	}

	public static void main(String[] args) {
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

		Scanner inp = new Scanner(System.in);
		BackendImplementation backend = new BackendImplementation(dijkstraGraph);
                Frontend Front = new Frontend(backend, inp);

		Front.MainMenu();

    } 




}
