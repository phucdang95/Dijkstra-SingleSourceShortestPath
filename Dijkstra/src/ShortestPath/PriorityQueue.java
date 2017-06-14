package ShortestPath;
//Minimum priority queue class
public class PriorityQueue {
	private BinaryHeap priorityQueue;

	public PriorityQueue(int c) {
		priorityQueue = new BinaryHeap(c);
	}

	public void Enqueue(Queue n) {
		priorityQueue.add(n);
	}

	public Queue Dequeue() {
		return priorityQueue.getMin();
	}

	public void DecreaseKey(Queue n) {
		priorityQueue.decreaseKey(n);
	}

	public boolean isEmpty() {
		return priorityQueue.isEmpty();
	}

	public boolean contains(int vNum) {
		return priorityQueue.contains(vNum);
	}
}
