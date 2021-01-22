package hw7;

public class Building implements Comparable<Building>{
	private String name;
	private String id;
	private boolean intersection;
	private double x;
	private double y;
	
	public Building(String name, String id, double x, double y) {
		this.name = name;
		if( name.equals("") ) {
			intersection = true;
		}else {
			intersection = false;
		}
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public String getName(){
		return new String(name);
	}
	public String getID() {
		return new String(id);
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public boolean isIntersection() {
		return intersection;
	}
	@Override
	public int compareTo(Building b) {
		if(this.name.equals(b.name)) {
			return this.id.compareTo(b.id);
		}
		return this.name.compareTo(b.name);
	}
}
