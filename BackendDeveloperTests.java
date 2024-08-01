import static org.junit.Assert.*;
import org.junit.Test;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.StringReader;

//Class that holds tests for backend
public class BackendDeveloperTests {

	//tests if backend read in the data correctly from a given file and check if it contains 
	//the right data.
	@Test 
	public void testReadDataFromFile(){
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

        	BackendImplementation backend = new BackendImplementation(dijkstraGraph);

		String filePath = "flights.dot"; 
		boolean success = backend.readDataFromFile(filePath);

		assertTrue(success);

	}

	//tests if the backend finds the correct/expected shortest path given the start and end airports 
	@Test 
	public void testFindShortestPath(){
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

                BackendImplementation backend = new BackendImplementation(dijkstraGraph);

		String filePath = "flights.dot"; 
                backend.readDataFromFile(filePath);

		String start = "ATL";
		String end = "SFO";

		shortestPathInterface result = backend.findShortestPath(start, end);

		assertTrue(result.getTotalMiles() > 0);

	}

	//tests if the summary contains output and returns the right stats.  
	@Test 
	public void testGetSummary(){
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

                BackendImplementation backend = new BackendImplementation(dijkstraGraph);

		String filePath = "flights.dot"; 
                backend.readDataFromFile(filePath);

		String stats = backend.getSummary();

		assertTrue(!stats.isEmpty());
		assertTrue(stats.contains("Total Nodes: "));
		assertTrue(stats.contains("Total Edges: "));
		assertTrue(stats.contains("Total Miles: "));

	}

	//tests if the backend will throw an error when given an invalid file name to read data from 
	@Test
	public void testInvalidFile(){
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

                BackendImplementation backend = new BackendImplementation(dijkstraGraph);

		String badPath = "invalid.csv";
		try{
			backend.readDataFromFile(badPath);
			fail("Expected IllegalArgumentException but did not occur.");
		}catch(IllegalArgumentException e){ 
			//this is good, because it is expecting an error, so test passes. 
		}

	}

	//tests if backend will throw an error when given an invalid start or end airport destination
	//to find the shortest path in between. 
	@Test 
	public void testInvalidStartOrEnd(){
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

                BackendImplementation backend = new BackendImplementation(dijkstraGraph);

		String filePath = "flights.dot"; 
                backend.readDataFromFile(filePath);

		String start = "XXX";
		String end = "SFO";

		try{
			backend.findShortestPath(start, end) ;
			fail("Expected IllegalArgumentException but did not occur.");
		}catch(NoSuchElementException e){
			//this is good, because it is expecting an error, so test passes. 
		}

	}


	//tests the ShowStats in frontend which indirectly tests the getSummary method of backend
	@Test
  	public void testIntegration1(){
		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> dijkstraGraph = new DijkstraGraph<>(map);

		TextUITester tester = new TextUITester("TEST");
    		Scanner inp = new Scanner(System.in);

		BackendImplementation backend = new BackendImplementation(dijkstraGraph);
                Frontend Front = new Frontend(backend, inp);

    		Front.ShowStats();
    		String output = tester.checkOutput();
    		assertTrue(output.startsWith("Dataset Summary:\n"));
  	}


	//tests if shortestPath returns the right output which indirectly tests the findShortestPath method of backend
	@Test
	public void testIntegration2() {
		TextUITester tester = new TextUITester("L\nflights.dot\nR\nORD\nSFO\nQ");
    		Scanner inp = new Scanner(System.in);
    		MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
    		DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>(map);
    		BackendInterface back = new BackendImplementation(graph);
    		FrontendInterface Front = new Frontend(back, inp);
    		Front.MainMenu();
    		String output = tester.checkOutput();
    		assertEquals("Hello, welcome to the Flight Router\n" + 
        "Please input one of the following:\n" + 
        "L- load file \n" + 
        "S- show stats \n" + 
        "R- get ShortestRoute \n" + 
        "Q- quit\n" + 
        "Please provide the path for the file to be read:\n" + 
        "Please input one of the following:\n" + 
        "L- load file \n" + 
        "S- show stats \n" + 
        "R- get ShortestRoute \n" + 
        "Q- quit\n" + 
        "please state starting airport:\n" + 
        "please state ending airport:\n" + 
        "Route: \n" + 
        "Airport, Miles\n" + 
        "Start:ORD\n" + 
        "SFO,1846\n" + 
        "Total miles:1846.0\n" + 
        "Please input one of the following:\n" + 
        "L- load file \n" + 
        "S- show stats \n" + 
        "R- get ShortestRoute \n" + 
        "Q- quit\n" + 
        "closing program...", output);

  	}

    //tests if frontend exits the app correctly 
    @Test
    public void testPartner1(){
		TextUITester tester = new TextUITester("L\nflights.dot\nQ");
		Scanner inp = new Scanner(System.in);

                MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>(map);
                
		BackendInterface back = new BackendImplementation(graph);
                FrontendInterface Front = new Frontend(back, inp);

		Front.MainMenu();
                String output = tester.checkOutput();

		assertEquals("Hello, welcome to the Flight Router\n" + 
        "Please input one of the following:\n" + 
        "L- load file \n" + 
        "S- show stats \n" + 
        "R- get ShortestRoute \n" + 
        "Q- quit\n" + 
        "Please provide the path for the file to be read:\n" +
	"Please input one of the following:\n" + 
        "L- load file \n" + 
        "S- show stats \n" + 
        "R- get ShortestRoute \n" + 
        "Q- quit\n" + 
	"closing program...", output);
   }

   //Tests if frontend displays the summary correctly 
   @Test
   public void testPartner2(){
	TextUITester tester = new TextUITester("L\nflights.dot\nS\nQ");
                Scanner inp = new Scanner(System.in);

                MapADT<String, DijkstraGraph<String, Double>.Node> map = new PlaceholderMap<>();
                DijkstraGraph<String, Double> graph = new DijkstraGraph<String, Double>(map);
                
                BackendInterface back = new BackendImplementation(graph);
                FrontendInterface Front = new Frontend(back, inp);

                Front.MainMenu();
                String output = tester.checkOutput();

		assertTrue(output.contains("Dataset Summary:\n" + 
        "Total Nodes: 58\n" + 
        "Total Edges: 1598\n" + 
        "Total Miles: 2142457.0\n\n"));


   }


}

