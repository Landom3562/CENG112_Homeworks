public interface PriorityQueueInterface<T extends Comparable<T>> {
	
	public void enqueue(T entry);
	
	public T dequeue();
	
	public T peek();
	
	public boolean isEmpty();
	
	public int getSize();
	
	public void clear();
	
	public T[] toArray();
}
