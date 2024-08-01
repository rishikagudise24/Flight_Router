import java.util.List;

public interface BackendInterface{
    /**
    *Constructor
    *Parameters: GraphADT 
    *IndividualBackendInterface(GraphADT graph);
    */

     /**
          * Reads data from a specified file and populates the graph.
            * @param filePath The path to the data file.
         */

    public boolean readDataFromFile(String filePath);

    /**
         * Finds the shortest route from a start to a destination airport in the dataset.
         * @param startAirport The starting airport.
         * @param destinationAirport The destination airport.
         * @return The result of the shortest path search.
         */

    public shortestPathInterface findShortestPath(String start, String end);

    /**
         * Returns a string with statistics about the dataset.
         * @return Statistics about the dataset, including the number of nodes, the number of edges, and the total miles.
         */

    public String getSummary();





}
