package hw7;
import java.util.*;

import hw4.Edge;
import hw4.Graph;
import java.lang.Math;

import java.io.*;

public class CampusPathParser {
	
	/**
	 *  @param: filename Name of file to read
	 *  @param: nameToBuilding maps building name to object
	 *  @param: IDToBuilding maps building ID to object
	 *  @param graph Graph object
	 *  @effects: read the input file and build the 3 graphs
	 */ 
	//Map: ID | building object
	public static void readData(String filename, SortedMap<String, Building> nameToBuilding, Map<String, Building> IDToBuilding, Graph<Building, Double> graph) 
    		throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;

        while ((line = reader.readLine()) != null) {
        	 String[] split = line.split(",");
             int i = line.indexOf(",");
             if ((i == -1) &&  split.length != 4) {
            	 throw new IOException("File "+filename+" not a CSV (NAME,ID,X,Y, file.");
             }             

             // Add ID and info to map
             Building temp = new Building(split[0], split[1], Double.parseDouble(split[2]), Double.parseDouble(split[3]) );
             nameToBuilding.put(split[1], temp);
             IDToBuilding.put(split[0], temp);
             graph.addNode(temp);
             
        } 
        reader.close();
    }
	
	/**
	 *  @param: filename Name of file to read
	 *  @param: nameToBuilding maps building name to object
	 *  @param graph Graph object
	 *  @effects: construct edges for graph object
	 */ 
	public static void readEdges(String filename, SortedMap<String, Building> buildingNameAndID, Graph<Building, Double> graph) 
			throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while( (line = reader.readLine()) != null) {
        	int i = line.indexOf(",");
        	String building1 = line.substring(0,i);
        	String building2 = line.substring(i+1);
        	//find the buildings from buildingNameAndID
        	Building b1 = buildingNameAndID.get(building1);
        	Building b2 = buildingNameAndID.get(building2);
        	//construct edge
        	Double label = Math.sqrt( (b1.getX() - b2.getX())*(b1.getX() - b2.getX()) + (b1.getY() - b2.getY())*(b1.getY() - b2.getY()));
        	graph.addEdge(b1, b2, label);
        	graph.addEdge(b2,b1,label);
        }
	reader.close();
	}
}
