import org.junit.jupiter.api.Test;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;


public class FrontendDeveloperTests {
  
  
  @Test
  public void Test1() {
    TextUITester tester = new TextUITester("Q");
    Scanner inp = new Scanner(System.in);
    BackendPlaceholder back = new BackendPlaceholder();
    FrontendInterface Front = new Frontend(back, inp);
    Front.MainMenu();
    String output = tester.checkOutput();
    Assertions.assertTrue(output.endsWith("closing program..."));

  }
  @Test
  public void Test2() {
    TextUITester tester = new TextUITester("TEST");
    Scanner inp = new Scanner(System.in);
    BackendPlaceholder back = new BackendPlaceholder();
    FrontendInterface Front = new Frontend(back, inp);
    Front.LoadFile();
    String output = tester.checkOutput();
    Assertions.assertTrue(output.endsWith("TEST"));

    }
  @Test
  public void Test3() {
    TextUITester tester = new TextUITester("testA\nTESTB");
    Scanner inp = new Scanner(System.in);
    BackendPlaceholder back = new BackendPlaceholder();
    FrontendInterface Front = new Frontend(back, inp);
    Front.shortestRoute();
    String output = tester.checkOutput();
    Assertions.assertTrue(output.contains("Total miles:"));
  }
  @Test
  public void Test4() {
    TextUITester tester = new TextUITester("TEST");
    Scanner inp = new Scanner(System.in);
    BackendPlaceholder back = new BackendPlaceholder();
    FrontendInterface Front = new Frontend(back, inp);
    Front.ShowStats();
    String output = tester.checkOutput();
    Assertions.assertTrue(output.endsWith("Summary\n"));
  }
  @Test
  public void Test5() {
    TextUITester tester = new TextUITester("l\nout\nq");
    Scanner inp = new Scanner(System.in);
    BackendPlaceholder back = new BackendPlaceholder();
    FrontendInterface Front = new Frontend(back, inp);
    Front.MainMenu();
    String output = tester.checkOutput();
    Assertions.assertTrue(output.endsWith("outPlease input one of the following:\n" + 
        "L- load file \n" + 
        "S- show stats \n" + 
        "R- get ShortestRoute \n" + 
        "Q- quit\n" + 
        "closing program..."));
}
}
