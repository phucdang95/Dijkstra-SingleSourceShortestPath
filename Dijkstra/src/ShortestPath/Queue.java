package ShortestPath;

public class Queue {
	private int Vnum;
	private double Distance;

	public Queue(int n, double d) {
		Vnum = n;
		Distance = d;
	}

	public int getVNum() {
		return Vnum;
	}

	public double getDistance() {
		return Distance;
	}
}
