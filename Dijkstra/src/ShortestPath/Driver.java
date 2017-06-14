package ShortestPath;

/*
 * Phuc Dang
 * CSC 4520 
 * Dijkstra's Shortest Path Algorithm
 * Resources: - https://en.wikipedia.org/wiki/Dijkstra's_algorithm
 * 			  - https://github.com/stewbob/dijkstra
 * 			  - http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
 * 			  - https://rosettacode.org/wiki/Dijkstra%27s_algorithm#Java
 * Sample Run: 
 * Enter input file's location: /Users/PhucDang/Google Drive/Programming/Elipse/Dijkstra/src/ShortestPath/1000.txt 
 * The Length of the Shortest Path Tree is 625349
 * */
import java.io.IOException;
import java.util.HashSet;
import java.util.*;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.io.File;

public class Driver {
	private static Scanner userInput;

	public static void main(String[] args) throws IOException {
		final int SourceOfVertex = 0;
		userInput = new Scanner(System.in);
		System.out.print("Enter input file's location: ");
		// Get input files
		File inputFile = new File(userInput.nextLine());
		if (inputFile.exists()) {
			Graph input = graphing(inputFile);
			// Print out shortest path
			System.out.println("The Length of the Shortest Path Tree is " + ShortestPath(input, SourceOfVertex));
		} else {
			System.out.println("File not found.");
		}
	}

	// Calculating the shortest path
	private static int ShortestPath(Graph graph, int s) {
		double[] shortestpath = Dijkstra_Algorithm(graph, s);
		double total = 0;
		for (double length : shortestpath) {
			total += length;
		}
		return (int) total;
	}

	// Create the graph from input file
	private static Graph graphing(File file) throws IOException {
		Scanner fileInput = new Scanner(file);
		// Parse the vertices
		String ParseVertices = fileInput.next(Pattern.compile("^n=\\d+"));
		int a = Integer.parseInt(ParseVertices.substring(2));
		fileInput.nextLine();
		// From adjacency list
		HashMap<Integer, HashSet<Edge>> Adjacency_list = new HashMap<Integer, HashSet<Edge>>();

		for (int i = 0; i < a; i++) {
			Adjacency_list.put(i, new HashSet<Edge>());
		}
		// Adding all the neighbors
		while (fileInput.hasNextLine()) {
			int cVertex = fileInput.nextInt();
			fileInput.nextLine();
			// Parse all the neighbors
			String ba = fileInput.nextLine().trim();
			HashSet<Edge> neighbors = Adjacency_list.get(cVertex);
			do {
				String[] Neighbor_Insert = ba.split("\\s+");
				int I_neighbor = Integer.parseInt(Neighbor_Insert[0]);
				int WeightofEdge = Integer.parseInt(Neighbor_Insert[1]);
				neighbors.add(new Edge(I_neighbor, WeightofEdge));
				Adjacency_list.get(I_neighbor).add(new Edge(cVertex, WeightofEdge));
				ba = fileInput.nextLine().trim();
			} while (!ba.isEmpty());
		}
		fileInput.close();
		return new Graph(a, Adjacency_list);
	}

	// Implement Dijkstra's algorithm to shortest path distance using heap
	private static double[] Dijkstra_Algorithm(Graph graph, int s) {
		int o = graph.getOrder();

		PriorityQueue MinimumPriorityQueue = new PriorityQueue(o);
		double[] DistanceFinal = new double[o];

		for (int j = 0; j < DistanceFinal.length; j++) {
			DistanceFinal[j] = Double.POSITIVE_INFINITY;
		}

		MinimumPriorityQueue.Enqueue(new Queue(s, 0));

		while (!MinimumPriorityQueue.isEmpty()) {
			Queue currentMin = MinimumPriorityQueue.Dequeue();

			int v = currentMin.getVNum();
			double distance = currentMin.getDistance();
			DistanceFinal[v] = distance;
			HashSet<Edge> NEIGHBOR = graph.getNeighbors(v);
			Iterator<Edge> N_Iter = NEIGHBOR.iterator();
			while (N_Iter.hasNext()) {
				Edge neighbor = N_Iter.next();
				int Num = neighbor.getVNum();
				if (DistanceFinal[Num] == Double.POSITIVE_INFINITY) {
					double Current_Distance = neighbor.getEdgeWeight();
					Queue N_Node = new Queue(Num, Current_Distance + distance);
					if (MinimumPriorityQueue.contains(Num)) {
						MinimumPriorityQueue.DecreaseKey(N_Node);
					} else {
						MinimumPriorityQueue.Enqueue(N_Node);
					}
				}
			}
		}
		return DistanceFinal;
	}
}

class Graph {
	private int orders;
	private HashMap<Integer, HashSet<Edge>> Adjacency_List;

	public Graph(int ordering, HashMap<Integer, HashSet<Edge>> Adjacency) {
		orders = ordering;
		Adjacency_List = Adjacency;
	}

	public int getOrder() {
		return orders;
	}

	public HashSet<Edge> getNeighbors(int v) {
		return Adjacency_List.get(v);
	}
}
