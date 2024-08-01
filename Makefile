all: runBDTests runFDTest BackendImplementation.class
	java BackendImplementation

runBDTests: BackendDeveloperTests.class
	java -cp .:../junit5.jar org.junit.platform.console.ConsoleLauncher --class-path . --select-class BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java BackendInterface.class BackendImplementation.class DijkstraGraph.class PlaceholderMap.class GraphADT.class MapADT.class
	javac -cp .:../junit5.jar $<

BackendInterface.class: BackendInterface.java
	javac $<

BackendImplementation.class: BackendImplementation.java BackendInterface.class GraphADT.class
	javac $<

DijkstraGraph.class: DijkstraGraph.java MapADT.class
	javac -cp .:../junit5.jar $<

PlaceholderMap.class: PlaceholderMap.java MapADT.class
	javac $<

GraphADT.class: GraphADT.java
	javac $<

MapADT.class: MapADT.java
	javac $<

clean:
	rm -f *.class

runFDTest: FrontendDeveloperTests.class
	java -jar junit5.jar --class-path=. --select-class=FrontendDeveloperTests


FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp junit5.jar:. FrontendDeveloperTests.java
