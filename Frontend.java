import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {
  BackendInterface back;
  Scanner in;
  Boolean active;
  public Frontend(BackendInterface backend, Scanner scan) {
    in = scan;
    back = backend;
  }
  
  public static void main (String[] Args) {
    Scanner inp = new Scanner(System.in);
    MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
    DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>(map);
    BackendInterface back = new BackendImplementation(graph);
    FrontendInterface Front = new Frontend(back, inp);
    Front.MainMenu();
    
    
  }
  public void MainMenu() {
    System.out.println("Hello, welcome to the Flight Router");
    active = true;
    String input;
    while (active) {
      System.out.println("Please input one of the following:"
          + "\nL- load file \nS- show stats \nR- get ShortestRoute \nQ- quit");
      input = in.nextLine();
      switch (input.toLowerCase()) {
        case "l":
          LoadFile();
          break;
        case "s":
          ShowStats();
          break;
        case "r":
          shortestRoute();
          break;
        case "q":
          exit();
          
      }
      
    }
    
  }
  
  public boolean LoadFile() {
    System.out.println("Please provide the path for the file to be read:");
    back.readDataFromFile(in.nextLine());
    return true;
  }
  public void ShowStats() {
    System.out.println(back.getSummary());
    
  }
  public void shortestRoute() {
    System.out.println("please state starting airport:");
    String start = in.nextLine();
    System.out.println("please state ending airport:");
    String end = in.nextLine();
    shortestPathInterface min = back.findShortestPath(start, end);
    System.out.println("Route: \nAirport, Miles");
    List<String> air = min.getRoute();
    List<Integer> miles = min.findMilesPerSegment();
    Iterator<String> airIt = air.iterator();
    Iterator<Integer> MilesIt = miles.iterator();
    System.out.println("Start:" + airIt.next());
    while( airIt.hasNext()) {
      System.out.println(airIt.next() +','+ String.valueOf(MilesIt.next()));
      }
    System.out.println("Total miles:" + String.valueOf(min.getTotalMiles()));
  }
  public void exit() {
    active = false;
    System.out.print("closing program...");
    
  }

}


