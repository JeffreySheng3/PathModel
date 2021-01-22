package hw4;
/** Edge represents an immutable edge object. It comprises of an edge label, source node, and end node.
 */
public class Edge<T extends Comparable<T>, N extends Comparable<N>> implements Comparable<Edge<T,N>>{
	private final N label;
	private final T source;
	private final T end;
	private final boolean reflexive;
	
	//Abstract function
	//  An edge represents the path between two nodes (source,end) that is labeled with "label"
	
	//Rep invaraint 
	// label && source && end != NULL
	
	/** @param label The label of this edge 
	 *  @param source The source node of this edge 
	 *  @param end The end node of this edge.
     *  @effects Constructs a new edge this.label = label, this.source = source, this.end = end.
    */
	public Edge(N label, T source, T end) {
		this.label = label;
		this.source = source;
		this.end = end;
		if(source == end) {
			reflexive = true;
		}else {
			reflexive = false;
		}
		checkRep();
	}
	/**
     * Returns the reflexive property of this edge
     *
     * @return true if reflexive, false if not
     */
	public boolean isReflexive() {
		return reflexive;
	}
	
	/**
     * Returns the label of this edge
     *
     * @return the label of this edge
     */
	public N getLabel() {
		return label;
	}
	
	/**
     * Returns the source node of this edge
     *
     * @return the source node of this edge
     */
	public T getSource() {
		return source;
	}
	
	/**
     * Returns the end node of this edge
     *
     * @return the end node of this edge
     */
	public T getEnd() {
		return end;
	}
	
	/** @param edge the edge to compare to
	 *  @returns -1 for less than, 0 for equals, 1 for more than
     *  @effects Constructs a new edge this.label = label, this.source = source, this.end = end.
    */
	public int compareTo(Edge<T,N> edge) {
		if(end.equals(edge.end)) {
			return label.compareTo(edge.label);
		}
		return end.compareTo(edge.end);
	}
	
	/**
     * Checks that the representation invariant holds (if any).
     **/
    // Throws a RuntimeException if the rep invariant is violated.
    private void checkRep() throws RuntimeException {
    	if(this.source == null) {
    		throw new RuntimeException("Source cannot be null!");
    	}else if(this.label == null) {
    		throw new RuntimeException("Label cannot be null!");
    	}else if(this.end == null) {
    		throw new RuntimeException("End cannot be null!");
    	}
    }

}
