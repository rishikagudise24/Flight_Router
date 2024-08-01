import java.util.ArrayList;
import java.util.List;

public class BackendPlaceholder implements BackendInterface{

  public class SPIPlaceholder implements shortestPathInterface{
    String start;
    String end;
    
    public SPIPlaceholder(String st, String en) {
      start=st;
      end=en;
          
    }
    
    @Override
    public List<String> getRoute() {
      List<String> out = new ArrayList<String>();
      out.add(start);
      out.add(end);
      
      return out;
    }

    @Override
    public List<Integer> findMilesPerSegment() {
      List<Integer> out = new ArrayList<Integer>();
      out.add(1);
      out.add(2);
      
      return out;
    }

	@Override
    public double getTotalMiles() {
      // TODO Auto-generated method stub
      return 5;
    }

}
    @Override
    public boolean readDataFromFile(String filePath) {
        System.out.print(filePath);
        // Provide implementation for reading data from file
        return true; // Assuming success for now
    }

    @Override
    public shortestPathInterface findShortestPath(String start, String end) {
        return new SPIPlaceholder(start, end);
    }

    @Override
    public String getSummary() {
        // Provide implementation for getting summary
        String summary = "Summary";
        return summary;
    }
}
