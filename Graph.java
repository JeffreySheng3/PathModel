package hw4;
import java.util.*;

/**Graph represents a map where the key is the node and the value are a list of edges
 */

// T = object, N = label
public class Graph<T extends Comparable<T>,N extends Comparable<N>>{
	private SortedMap<T, ArrayList<Edge<T,N>> > map;
	
	//Abstract function
	//  A map is made up of a collection of edges and nodes. The map contains the nodes as keys and the values as a list of
	//edges which the nodes connect to
	
	//Rep invariant
	// if map.size() != 0{
	//  forall i in map, i.label && i.source && i.end != NULL
	// }
	
	/**
     * @effects Constructs a new graph object  
     */
	public Graph() {
		map = new TreeMap<>();
		//checkRep();
	}
	
	public int numberOfNodes() {
		return map.size();
	}
	
	public int numberOfEdges(T nodeName) {
		return map.get(nodeName).size();
	}
	
	/**
	 * @param parentNode Parent node for this edge
	 * @param childNode Child node for this edge
	 * @param edgeLabel Label for this edge
     * @modifies map
     * @effects adds a new edge into map. If either source or end node does not exist, create it.     
     */
	public void addEdge(T parentNode, T childNode, N edgeLabel) {
		//if nodes arent in the map, place them in
		if(!map.containsKey(parentNode)) {
			addNode(parentNode);
		}
		if(!map.containsKey(childNode)) {
			addNode(childNode);
		}
		//get the list of edges and place current edge in, then sort
		ArrayList<Edge<T,N>> temp = map.get(parentNode);
		temp.add( new Edge<T,N>(edgeLabel,parentNode,childNode) );
		//checkRep();
	}
	
	/**
	 * @param nodeData Label for node key value
     * @modifies map
     * @effects creates new key(node) in map, if node already exists then do nothing
     */
	public void addNode(T nodeData) {
		if(map.containsKey(nodeData)) {
			return;
		}else {
			map.put(nodeData, new ArrayList<Edge<T,N>>());
		}
		//checkRep();
	}
	
	public boolean contains(T node) {
		return map.containsKey(node);
	}
	 
	/**
	 * @requires map != NULL
     * @return an iterator which goes through the nodes in alphabetical order
     */
	public Iterator<T> listNodes() {
		//convert keys to a set and return iterator to it
		for(ArrayList<Edge<T,N>> edge : map.values()) {
			Collections.sort(edge);
		}
		Set<T> temp = map.keySet();
		Iterator<T> itr = temp.iterator();
		return itr;
	}
	
	/**
	 * @requires map != NULL
	 * @param parentNode The node to start at
     * @return  an iterator which goes through the child nodes of the parent node in lexicographical order by name then by edge label
     */
	public Iterator<String> listChildren(T parentNode){
		for(ArrayList<Edge<T,N>> edge : map.values()) {
			Collections.sort(edge);
		}
		//place the nodes into arraylist
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<Edge<T,N>> edges = map.get(parentNode);
		//find the edges and format according to if reflexive or not
		for(int i = 0; i < edges.size(); i++) {
			if(edges.get(i).isReflexive()) {
				temp.add(edges.get(i).getSource().toString() + "(" + edges.get(i).getLabel().toString() + ")");
			}else {
				temp.add(edges.get(i).getEnd() + "(" + edges.get(i).getLabel() + ")");
			}
		}
		Iterator<String> itr = temp.iterator();
		return itr;
	}
	
	/**
	 * @requires map.contains(key)
	 * @param key The node that we want to return edges for
     * @effects returns an iterator to the edges that has the key as its source node in lexicographical order  
     */
	public Iterator<Edge<T,N>> edgeItr(T key){
		ArrayList<Edge<T,N>> edge = map.get(key);
		Collections.sort(edge);
		Iterator<Edge<T,N>> itr = map.get(key).iterator();
		return itr;
	}
	
	/**
     * Checks that the representation invariant holds (if any).
     **/
    // Throws a RuntimeException if the rep invariant is violated.
	/*
    private void checkRep() throws RuntimeException {
    	if(map.size() != 0) {
    		Iterator<T> temp = listNodes();
    		ArrayList<T> values = new ArrayList<>();
    		ArrayList<Edge<T,N>> edges = new ArrayList<>();
    		while(temp.hasNext()) {
    			values.add(temp.next());
    		}
    		for(int i = 0; i < values.size(); i++) {
    			edges = map.get(values.get(i));
    			for(int j = 0; j < edges.size(); j++) {
    				if(edges.get(j).getEnd() == null) {
    					throw new RuntimeException("End cannot be null!");
    				}else if(edges.get(j).getSource() == null) {
    					throw new RuntimeException("Source cannot be null!");
    				}else if(edges.get(j).getLabel() == null) {
    					throw new RuntimeException("Label cannot be null!");
    				}
    			}
    		}
    	} 
    }
    */
	
    /**
     * @effects clears the map of data 
     **/
    public void clear() {
    	map.clear();
    }

}
