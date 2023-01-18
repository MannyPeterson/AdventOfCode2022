import java.util.ArrayList;

public class P12BestPath {
	
	static int blah = 0;
	
	public void poo() {
		System.out.println(blah);
	}

	public P12Coordinate find(int[][] map, P12Coordinate start, P12Coordinate end, ArrayList<P12Coordinate> visited) {

		P12BestPath child = new P12BestPath();

		P12Coordinate[] neighbors = start.getNeighbors();
		
		if(visited == null) {
			visited = new ArrayList<P12Coordinate>();
		}
		
		//ArrayList<P12Coordinate> copy = new ArrayList<P12Coordinate>();
		

		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i] != null) {
				if(visited.contains(neighbors[i]) != true) {
					if (((start.getMapAt(map) + 0) == neighbors[i].getMapAt(map))
							|| ((start.getMapAt(map) + 1) == neighbors[i].getMapAt(map))) {
						System.out.println(neighbors[i] + " " + start.getMapAt(map) + " " + neighbors[i].getMapAt(map));
						visited.add(start);
						if(neighbors[i].getMapAt(map) > blah) {
							blah = neighbors[i].getMapAt(map);
						}

						child.find(map, neighbors[i], end, visited);
					}
				}

			}
		}

		return null;

	}
}
