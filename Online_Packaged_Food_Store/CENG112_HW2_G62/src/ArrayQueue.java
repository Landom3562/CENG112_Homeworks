
public final class ArrayQueue<T> implements QueueInterface<T> {
	
	private T[] queue;
	private int capacity;
	private int frontIndex;
	private int backIndex;
	
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[capacity];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = capacity - 1;
	}
	
	public void enqueue(T newEntry) {
		ensureCapacity();
		backIndex = (backIndex + 1) % capacity;
		queue[backIndex] = newEntry;
	}
	
	public T dequeue() {
		if(isEmpty()) {
			return null;
		}
		T returned = queue[frontIndex];
		queue[frontIndex] = null;
		frontIndex = (frontIndex + 1) % capacity;
		return returned;
	}
	
	public T getFront() {
		return queue[frontIndex];
	}
	
	public boolean isEmpty() {
		return ((backIndex + 1) % capacity == frontIndex) && (queue[backIndex] == null);
	}
	
	public void clear() {
		while(!isEmpty()) {
			dequeue();
		}
	}
	
	public T[] toArray() {
		if(isEmpty()) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[capacity];
		int index = frontIndex;
		int size = frontIndex > backIndex ? (capacity - frontIndex + backIndex + 1) : (backIndex - frontIndex + 1);
		for(int i = 0; i < size ;i++){
			array[i] = queue[index];
			index =  (index + 1) % capacity;
		}
		return array;
	}
	
	private void ensureCapacity() {
		if(((backIndex + 1) % capacity == frontIndex) && queue[backIndex] != null) {
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[capacity * 2];
			for(int i = 0; i < capacity; i++) {
				tempQueue[i] = dequeue();
			}
			queue = tempQueue;
			frontIndex = 0;
			backIndex = capacity - 1;
			capacity *=  2;
		}
	}
}
