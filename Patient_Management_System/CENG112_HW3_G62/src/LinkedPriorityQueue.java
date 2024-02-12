public class LinkedPriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {
	
	private Node firstNode;
	private int numberOfEntries;
	
	public void enqueue(T entry) {
		Node newNode = new Node(entry);
		Node nodeBefore = getNodeBefore(entry);
		if (isEmpty() || (nodeBefore == null)) {
			newNode.setNext(firstNode);
			firstNode = newNode;
		}else {
			Node nodeAfter = nodeBefore.getNext();
			newNode.setNext(nodeAfter);
			nodeBefore.setNext(newNode);
		}
		numberOfEntries++;
	}
	
	public T dequeue() {
		assert(!isEmpty());
		T removed = firstNode.getData();
		firstNode = firstNode.getNext();
		numberOfEntries--;
		return removed;
	}
	
	public T peek() {
		assert(!isEmpty());
		return firstNode.getData();
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}
	
	public int getSize() {
		return numberOfEntries;
	}
	
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while((index < numberOfEntries)&&(currentNode != null)) {
			array[index] = currentNode.getData();
			currentNode = currentNode.getNext();
			index++;
		}
		return array;
	}
	
	private Node getNodeBefore(T anEntry) {
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while((currentNode != null) && (anEntry.compareTo(currentNode.getData())>0)) {
			nodeBefore = currentNode;
			currentNode = currentNode.getNext();
		}
		return nodeBefore;
	}	

	private class Node{
		private T data;
		private Node next;
		
		private Node(T data) {
			this(data,null);
		}
		private Node(T anEntry, Node nextNode) {
			data = anEntry;
			next = nextNode;
		}
		private T getData() {
			return data;
		}
		
		private Node getNext() {
			return next;
		}
		private void setData(T entry) {
			data = entry;
		}
		private void setNext(Node nextNode) {
			next = nextNode;
		}
	}
}
