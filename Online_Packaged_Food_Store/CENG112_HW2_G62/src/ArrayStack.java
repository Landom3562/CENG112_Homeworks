
public final class ArrayStack<T> implements StackInterface<T> {
	
	private T[] stack;
	private int topIndex;
	private int capacity;
	
	public ArrayStack(int capacity) {
		this.capacity = capacity;
		 @SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[capacity];
		stack = tempStack;
		topIndex = -1;
	}
	public void push(T newEntry) {
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	}
	
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		else {
			T popped = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return popped;
		}
	}
	
	public T peek() {
		return stack[topIndex];
	}
	
	public boolean isEmpty() {
		return topIndex == -1;
	}
	
	public void clear() {
		while(!isEmpty()) {
			pop();
		}
	}
	
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[topIndex+1];
		for(int i = 0; i <= topIndex; i++) {
			array[i] = stack[i];
		}
		return array;
	}
	
	private void ensureCapacity() {
		if(topIndex == capacity - 1) {
			 @SuppressWarnings("unchecked")
			T[] tempStack = (T[]) new Object[capacity * 2];
			for(int i = 0; i < capacity; i++) {
				tempStack[i] = stack[i];
			}
			stack = tempStack;
			capacity *= 2;
		}
	}
	
}
