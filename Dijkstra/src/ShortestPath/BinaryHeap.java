package ShortestPath;

public class BinaryHeap {
	private Queue[] minimumHeap;
	private int[] key;
	private int capacity;
	private final int NotInHeap = -1;

	public BinaryHeap(int c) {
		minimumHeap = new Queue[c];
		key = new int[c];
		for (int j = 0; j < key.length; j++) {
			key[j] = NotInHeap;
		}
		capacity = 0;
	}

	public void add(Queue n) {
		int p = capacity;
		Heapify_UP(p, n);
		capacity++;
	}

	public Queue getMin() {
		int first = 0;
		int last = capacity - 1;
		Queue smallest = minimumHeap[first];
		key[smallest.getVNum()] = NotInHeap;
		Heapify_Down(first, minimumHeap[last]);
		capacity--;
		return smallest;
	}

	public void decreaseKey(Queue n) {
		int v = n.getVNum();
		int gotHeap = key[v];
		if (n.getDistance() < minimumHeap[gotHeap].getDistance()) {
			Heapify_UP(gotHeap, n);
		}
	}

	private void placeNode(Queue n, int CurrentPosition) {
		minimumHeap[CurrentPosition] = n;
		int v = minimumHeap[CurrentPosition].getVNum();
		key[v] = CurrentPosition;
	}

	private void Heapify_UP(int CurrentPosition, Queue n) {
		if (CurrentPosition == 0) {
			placeNode(n, CurrentPosition);
			return;
		}
		int parent = (CurrentPosition - 1) / 2;
		if (n.getDistance() < minimumHeap[parent].getDistance()) {
			placeNode(minimumHeap[parent], CurrentPosition);
			Heapify_UP(parent, n);
		} else {
			placeNode(n, CurrentPosition);
		}
	}

	private void Heapify_Down(int CurrentPosition, Queue n) {
		if (isLeaf(CurrentPosition)) {
			placeNode(n, CurrentPosition);
			return;
		}
		int leftChild = 2 * CurrentPosition + 1;
		int smallestChild = leftChild;
		if (hasRightChild(CurrentPosition)) {
			int rightChild = leftChild + 1;
			smallestChild = minimumHeap[leftChild].getDistance() < minimumHeap[rightChild].getDistance() ? leftChild
					: rightChild;
		}
		if (n.getDistance() > minimumHeap[smallestChild].getDistance()) {
			placeNode(minimumHeap[smallestChild], CurrentPosition);
			Heapify_Down(smallestChild, n);
		} else {
			placeNode(n, CurrentPosition);
		}
	}

	private boolean isLeaf(int NODE_KEY) {
		return (2 * NODE_KEY + 1 > capacity - 1);
	}

	public boolean hasRightChild(int NODE_KEY) {
		return (2 * NODE_KEY + 2 <= capacity - 1);
	}

	public boolean isEmpty() {
		return (capacity == 0);
	}

	public boolean contains(int vNum) {
		return (key[vNum] > NotInHeap);
	}
}
