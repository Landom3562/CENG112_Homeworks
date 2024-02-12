
public interface SortedListInterface<T extends Comparable<T>> {
	
	public void add(T entry);
	
	public T remove();
	
	public T remove(T anEntry);
	
	public T remove(int givenPosition);
	
	public int getPosition(T anEntry);
	
	public T getEntry(int givenPosition);
	
	public boolean contains(T entry);
	
	public void clear();
	
	public int getLenght();
	
	public boolean isEmpty();
	
	public T[] toArray();
}
