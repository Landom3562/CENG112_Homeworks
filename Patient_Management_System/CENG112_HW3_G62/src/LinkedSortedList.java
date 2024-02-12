import java.time.LocalDateTime;

public class LinkedSortedList<T extends Comparable<T>> implements SortedListInterface<T> {
	private Node firstNode;
	private int numberOfEntries;
	
	public LinkedSortedList() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public void add(T entry) {
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
	
	public T remove() { //Removes the first element from the sorted list
		assert(!isEmpty());
		T removed = firstNode.getData();
		firstNode = firstNode.getNext();
		numberOfEntries--;
		return removed;
	}
	
	public T remove(T anEntry) { //Removes the desired element
		assert(!isEmpty());
		Node nodeBefore = getNodeBefore(anEntry);
		Node nodeToBeRemoved = nodeBefore.getNext();
		Node nodeAfter = nodeToBeRemoved.getNext();
		T removed = nodeToBeRemoved.getData();
		nodeBefore.setNext(nodeAfter);
		numberOfEntries--;
		return removed;
	}
	
	public T remove(int givenPosition) {
		assert(!isEmpty());
		Node nodeBefore = firstNode;
		Node nodeToRemove = firstNode.getNext();
		if(givenPosition == 1) {
			return remove();
		}
		for(int i = 2; i <= givenPosition; i++) {
			if(i == givenPosition) {
				break;
			}else {
				nodeBefore = nodeBefore.getNext();
				nodeToRemove = nodeToRemove.getNext();
			}
		}
		T removed = nodeToRemove.getData();
		nodeBefore.setNext(nodeToRemove.getNext());
		numberOfEntries--;
		return removed;
	}
	
	
	public int getPosition(T anEntry) {
		int position = 1;
		Node currentNode = firstNode;
		while(!isEmpty() && currentNode != null) {
			if(currentNode.getData().equals(anEntry)) {
				return position;
			}else {
				currentNode = currentNode.getNext();
			}
			position++;
		}
		return -1;
	}
	
	public T getEntry(int givenPosition) {
		assert(givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		T returned = null;
		for(int i = 1; i <= givenPosition; i++) {
			if(i == givenPosition) {
				returned = currentNode.getData();
			}else {
				currentNode = currentNode.getNext();
			}
		}
		return returned;
	}
	
	public boolean contains(T Entry) {
		return getPosition(Entry) > 0;
	}
	
	public void clear() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	public int getLenght() {
		return numberOfEntries;
	}
	
	public boolean isEmpty() {
		return numberOfEntries == 0;
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
		int counter = 0;
		Patient entry = (Patient) anEntry;
		Node currentNode = firstNode;
		Node nodeBefore = null;
		while((currentNode != null) && (counter < numberOfEntries)) {
			Patient currentPatient = (Patient) currentNode.getData();
			if(entry.date_time.isAfter(currentPatient.date_time)) {
				nodeBefore = currentNode;
				currentNode = currentNode.getNext();
			}
			counter++;
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
