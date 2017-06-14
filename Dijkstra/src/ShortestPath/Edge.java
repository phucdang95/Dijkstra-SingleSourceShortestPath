package ShortestPath;

public class Edge {
	private int vNum;
	private int eWeight;

	public Edge(int n, int d) {
		vNum = n;
		eWeight = d;
	}

	public int getVNum() {
		return vNum;
	}

	public int getEdgeWeight() {
		return eWeight;
	}

	public String toString() {
		return vNum + " --- " + eWeight;
	}
}
