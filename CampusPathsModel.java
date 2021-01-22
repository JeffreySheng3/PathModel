package hw7;
import java.io.IOException;
import java.util.*;
import hw4.*;
import hw6.Path;

public class CampusPathsModel extends Observable{
	private Graph<Building, Double> graph;
	private SortedMap<String, Building> buildings;
	private Map<String, Building> IDToBuildings;
	
	/**
	 *  @param: dataNodes the input for nodes
	 *  @param: dataEdges the input for edges
	 *  @effects: construct new cpm object
	 */ 
	public CampusPathsModel(String dataNodes, String dataEdges) {
		graph = new Graph<Building, Double>();
		buildings = new TreeMap<String, Building>();
		IDToBuildings = new HashMap<String, Building>();
		createNewGraph(dataNodes, dataEdges);
	}
	
	/**
	 *  @param: dataNodes node input
	 *  @param: dataEdges edge input
	 *  @effects: fill the graph object
	 */ 
	private void createNewGraph(String dataNodes, String dataEdges) {
		graph.clear();
		try {
			CampusPathParser.readData(dataNodes, buildings, IDToBuildings, graph);
			CampusPathParser.readEdges(dataEdges, buildings, graph);
			
			
			
		}catch (IOException e) {
			//catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
		
	}
	/**
	 *  @effects: lists all buildings in alphabetical order
	 */ 
	public void listBuildings() {
		ArrayList<Building> setOfBuildings = new ArrayList<Building>();
		for(String key : buildings.keySet()) {
			Building temp = buildings.get(key);
			if(temp.getName().equals("")) {
				continue;
			}
			setOfBuildings.add(temp);
		}
		Collections.sort(setOfBuildings);
		for(Building temp : setOfBuildings) {
			System.out.println(temp.getName() + "," + temp.getID());
		}
	}
	
	/**
	 *  @param: start name of start
	 *  @param: finish name of end
	 *  @effects: find shortest path between start and finish
	 *  @return: path between start and finish
	 */ 
	public Path<Building> findShortestPath(String start, String finish) {
		boolean flag1 = false;
		boolean flag2 = false;
		Building b1;
		Building b2;
		//if start is ID
		if(Utility.isNumber(start)) {
			//if ID is not in the map, or the id is an intersection
			if(buildings.get(start) == null || buildings.get(start).isIntersection()) {
				System.out.println("Unknown building: [" + start + "]");
				flag1 = true;
				b1 = null;
			}else {
				b1 = buildings.get(start);
			}
		//start is a name
		}else {
			if(!IDToBuildings.containsKey(start)) {
				System.out.println("Unknown building: [" + start + "]");
				flag1 = true;
				b1 = null;
			}else {
				b1 = IDToBuildings.get(start);
			}
		}
		if(start.equals(finish) && flag1) {
			return new Path<Building>();
		}
		//if finish is ID
		if(Utility.isNumber(finish)) {
			//if ID is not in the map, or the id is an intersection
			if(buildings.get(finish) == null || buildings.get(finish).isIntersection()) {
				System.out.println("Unknown building: [" + finish + "]");
				flag2 = true;
				b2 = null;
			}else {
				b2 = buildings.get(finish);
			}
		//start is a name
		}else {
			if(!IDToBuildings.containsKey(finish)) {
				System.out.println("Unknown building: [" + finish + "]");
				flag2 = true;
				b2 = null;
			}else {
				b2 = IDToBuildings.get(finish);
			}
		}
		
		if(flag1 || flag2) {
			return new Path<Building>();
		}
		Path<Building> temp = Utility.findPath(b1, b2, graph);
		ArrayList<Edge<Building, Double>> array = temp.getEdges();
		
		if(array.size() == 0 && !b1.getName().equals(b2.getName())) {
			String answer = "There is no path from " + b1.getName() + " to " + b2.getName() +".";
			System.out.print(answer);
			return new Path<Building>();
		}
		
		String answer = "Path from " + b1.getName() + " to " + b2.getName() + ":\n";
		for(Edge<Building, Double> d : array) {
			Building b = d.getEnd();
			String name = b.getName();
			Building beg = d.getSource();
			String direction = Utility.getDirection(beg, b);
			boolean flag = false;
			if(b.isIntersection()) {
				name = b.getID();
				flag = true;
			}
			if(flag) {
				answer += "\tWalk " + direction + " to (Intersection " + name + ")\n";
			}else {
				answer += "\tWalk " + direction + " to (" + name + ")\n";
			}
			
		}
		answer += String.format("Total distance: %.3f", temp.getCost()) + " pixel units.\n";
		System.out.print(answer);
		return temp;
	}
	
	/**
	 *  @return: map of name to building
	 */ 
	public Map<String, Building> getMap(){
		return buildings;
	}
}
