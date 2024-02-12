
public final class ArrayList<T> implements ListInterface<T>{

	private T[] list;
	private int lastIndex;
	private int capacity;
	
	public ArrayList(int capacity) {
		this.capacity = capacity;
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[capacity + 1];
		list = tempList;
		lastIndex = 0;
	}
	
	public void add(T newEntry) {
		ensureCapacity();
		lastIndex += 1;
		list[lastIndex] = newEntry; 
	}
	
	public void add(int givenPosition, T newEntry){
		ensureCapacity();
		if((givenPosition < 1) || (givenPosition > capacity)) {
			throw new IndexOutOfBoundsException("Given position is out of bounds.");
		}
		
		for(int i = lastIndex; i >= givenPosition; i--){
			list[i + 1] = list[i];
		}
		list[givenPosition] = newEntry;
		lastIndex++;
	}
	
	public T remove(int givenPosition) {
		if(isEmpty() || givenPosition < 1 || givenPosition > lastIndex) {
			return null;
		}
		T returned = list[givenPosition];
		for(int i = givenPosition; i < lastIndex; i++) {
			list[i] = list[i+1];
		}
		list[lastIndex] = null;
		lastIndex--;
		return returned;
	}
	
	public void clear() {
		while(!isEmpty()) {
			remove(lastIndex);
		}
	}
	
	public T replace(int givenPosition, T newEntry) {
		if((givenPosition < 1) || (givenPosition > capacity)) {
			throw new IndexOutOfBoundsException("Given position is out of bounds.");
		}
		T returned = list[givenPosition];
		list[givenPosition] = newEntry;
		return returned;
	}
	
	public T getEntry(int givenPosition) {
		return list[givenPosition];
	}
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[lastIndex];
		for(int i = 1; i <= lastIndex; i++) {
			array[i-1] = list[i];
		}
		return array;
	}
	
	public boolean contains(T entry) {
		boolean found = false;
		int i = 1;
		while(!found && i <= lastIndex) {
			if(entry == list[i]) {
				found = true;
			}
			i++;
		}
		return found;
	}
	
	public int getLenght() {
		return lastIndex;
	}
	
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	
	private void ensureCapacity() {
		if(lastIndex == capacity) {
			@SuppressWarnings("unchecked")
			T[] tempList = (T[]) new Object[(capacity*2) + 1];
			for(int i = 1; i < capacity + 1; i++) {
				tempList[i] = list[i];
			}
			list = tempList;
			capacity *= 2;
		}
	}
}
