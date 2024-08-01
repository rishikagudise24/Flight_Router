public interface FrontendInterface {

  /*
   * This constructor initializes a frontend with the passed backend 
   * it does NOT start the main menu in order to enable more detailed testing
   * public IndividualFrontendInterface(backendInterface backend, Scanner );
   */


  //This command starts the main menu, it finishes executing only when the user is done with the program
  public void MainMenu();


  //this command method takes a string filepath from the user and passes the backend whatever it needs to load the file
  //return true if file is successfully loaded.
  public boolean LoadFile();


  //gets a collection of information from the backend such as the number of nodes, edges and number of miles loaded in the database
  public void ShowStats();


  //this command asks the user for a start and destination airport and returns the shortest route between them
  public void shortestRoute();


  //this command is to exit the app, doesn't do much outside of when called by MainMenu()
  public void exit();


}
