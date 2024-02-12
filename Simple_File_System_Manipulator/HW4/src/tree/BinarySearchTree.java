package tree;

import java.util.List;
import exception.HaveToBeImplementedException;

public class BinarySearchTree<K extends Comparable<K>, T> extends Tree<T> {
	private BinarySearchTree(K key, T data) {
		setKey(key);
		setData(data);
	}
	
	public BinarySearchTree() {
		this(null, null);
	}
	
	public K getKey() {
		throw new HaveToBeImplementedException("BinarySearchTree.getKey()");
	}
	
	protected void setKey(K key) {
		throw new HaveToBeImplementedException("BinarySearchTree.setKey(K key)");
	}
	
	public void add(K key, T data) {
		throw new HaveToBeImplementedException("BinarySearchTree.add(K key, T data)");
	}

	public void remove(K key) {
		throw new HaveToBeImplementedException("BinarySearchTree.remove(K key)");
	}
	
	public BinarySearchTree<K, T> getLeftSubtree() {
		throw new HaveToBeImplementedException("BinarySearchTree.getLeftSubtree()");
	}
	
	public BinarySearchTree<K, T> getRightSubtree() {
		throw new HaveToBeImplementedException("BinarySearchTree.getRightSubtree()");
	}
	
	@Override
	public List<Tree<T>> getChildren() {
		throw new HaveToBeImplementedException("BinarySearchTree.getChildren()");
	}

	public BinarySearchTree<K, T> find(K key) {
		throw new HaveToBeImplementedException("BinarySearchTree.find(K key)");
	}
}
