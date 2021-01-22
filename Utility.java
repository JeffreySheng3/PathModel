package hw7;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

import hw4.Edge;
import hw4.Graph;
import hw6.Path;

public class Utility {
	
	/**
	 *  @param: str The string to read
	 *  @return true if string is a number, false if not
	 */ 
	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 *  @param: b1 the source building
	 *  @param: b2 the end building
	 *  @effects: calculate and output the direction from b1 to b2
	 *  @returns: string for direction 
	 */ 
	public static String getDirection(Building b1, Building b2) {
		double x = b2.getX() - b1.getX();
		double y = b2.getY() - b1.getY();
		double angle = Math.toDegrees(Math.atan2(x,y));
		String direction = "";
		if(112.5 <= angle && angle < 157.5) {
			direction = "NorthEast"; 
		}else if( 67.5 <= angle && angle < 112.5 ){ 
			direction = "East"; 
		}else if( 22.5 <= angle && angle < 67.5 ){ 
			direction = "SouthEast"; 
		}else if( -22.5 <= angle && angle < 22.5 ){ 
			direction = "South"; 
		}else if( -67.5 <= angle && angle < -22.5 ){ 
			direction = "SouthWest"; 
		}else if( -112.5 <= angle && angle < -67.5 ){ 
			direction = "West"; 
		}else if( -157.5 <= angle && angle < -112.5 ){ 
				direction = "NorthWest";
		}else if( 157.5 <= angle || angle < -157.5 ){ 
			direction = "North"; 
		}
		return direction;
	}
	
	/**
	 *  @param: char1 The first building
	 *  @param: char2 the second building
	 *  @param myGraph Graph object
	 *  @effects: read the input file and build the 3 graphs
	 *  @return: a shortest path from char1 to char2
	 */ 
	public static Path<Building> findPath(Building char1, Building char2, Graph<Building, Double> myGraph) {
		Path<Building> path = new Path<Building>(char1);
		//each element is a path from start to a given node
		PriorityQueue<Path<Building>> active = new PriorityQueue<Path<Building>>();
		//nodes that we know the minimum cost path for
		Map<Building, Path<Building>> finished = new HashMap<Building, Path<Building>>();
		

		active.add(path);
		boolean flag = false;
		//while active is non-empty
		while(active.size() != 0) {
			Path<Building> minPath = active.poll();
			Building minDest = minPath.getDestination();
			//if we reach the destination, record current path and break
			if(minDest.equals(char2)) {
				flag = true;
				path = minPath;
				break;
			}
			//if minDest is in finished, skip
			if(finished.containsKey(minDest)) {
				continue;
			}
			Iterator<Edge<Building, Double>> itr = myGraph.edgeItr(minDest);
			//for each edge that has minDest as its source
			while(itr.hasNext()) {
				Edge<Building, Double> edge = itr.next();
				//if the child is in finished, skip 
				if(finished.containsKey(edge.getEnd())) {
					continue;
				}
				//if child is not in finished
				Path<Building> newPath = new Path<Building>(minPath);
				newPath.addEdge(edge);
				newPath.addCost(edge.getLabel());
				newPath.setDestination(edge.getEnd());
				//add newPath to active
				active.add(newPath);
			}
			//add minDest to finished
			finished.put(minDest, minPath);
		}
		//if path is found, format
		if(flag) {
			return path;
		}
		return new Path<Building>(char1, char2, null);
	}
}
