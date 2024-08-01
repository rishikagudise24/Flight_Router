import java.util.List;

public interface shortestPathInterface{

    /**
    *returns a list of airports along the shortest route
    *@return List of Airports (in string format)
    */
        public List<String> getRoute();

    /**
    *Return a list of miles to travel for each segment on the shortest route 
    *@return list of miles
    */

     public List<Integer> findMilesPerSegment();

    /**
    *finds the combined total miles for the shortest path
    *@return total miles (in integer format)
    */

        public double  getTotalMiles();


}
