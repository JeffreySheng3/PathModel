package hw7;
import java.util.*;


public class CampusPaths{
	
	public static void main(String[] args) {
		CampusPathsModel cpm = new CampusPathsModel("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			//list all buildings
			if(line.equals("b")) {
				cpm.listBuildings();
			//shortest path
			}else if(line.equals("r")) {
				System.out.print("First building id/name, followed by Enter: ");
				String start = scanner.nextLine();
				System.out.print("Second building id/name, followed by Enter: ");
				String finish = scanner.nextLine();
				cpm.findShortestPath(start,finish);
			//list all options
			}else if(line.equals("m")) {
				System.out.println("All commands:\nb -- list all buildings\nr -- find directions for shortest route between two buildings"
						+ "\n m -- display a menu of all commands\nq -- quit");
			//quit
			}else if(line.equals("q")) {
				break;
			}else {
				System.out.println("Unknown option");
			}
		}
		scanner.close();
	}
	
	
}