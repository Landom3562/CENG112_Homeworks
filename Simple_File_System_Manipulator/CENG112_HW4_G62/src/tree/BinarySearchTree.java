package tree;

import java.util.List;

import java.util.ArrayList;


public class BinarySearchTree<K extends Comparable<K>, T> extends Tree<T> {
	
	private K key;
	private BinarySearchTree<K, T> leftSubtree = null;
	private BinarySearchTree<K, T> rightSubtree = null;
	
	
	private BinarySearchTree(K key, T data) {
		setKey(key);
		setData(data);
	}
	
	public BinarySearchTree() {
		this(null, null);
	}
	
	public K getKey() {
		return this.key;
	}
	
	protected void setKey(K key) {
		this.key = key;
	}
	
	public void add(K key, T data) {
		if(isNull(this)) {
			this.setKey(key);
			this.setData(data);
			this.setParent(null);
			this.leftSubtree = null;
			this.rightSubtree = null;
		}else {
			addEntry(getRoot(this), key, data);
		}
	}
	
	public void remove(K key) {
		BinarySearchTree<K, T> willRemoved = find(key);
		if(willRemoved == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		BinarySearchTree<K, T> parent = (BinarySearchTree<K, T>) willRemoved.getParent();
		BinarySearchTree<K, T> leftOfRemoved = willRemoved.getLeftSubtree();
		BinarySearchTree<K, T> rightOfRemoved = willRemoved.getRightSubtree();
		if(parent != null) {
			if(leftOfRemoved == null && rightOfRemoved == null) {
				reorder(willRemoved, null);
			}else if(leftOfRemoved != null && rightOfRemoved == null) {
				reorder(willRemoved, leftOfRemoved);
			
			}else if(leftOfRemoved == null && rightOfRemoved != null) {
				reorder(willRemoved, rightOfRemoved);
			}else {
				BinarySearchTree<K, T> largestOfLeft = findLargest(leftOfRemoved);
				willRemoved.setData(largestOfLeft.getData());
				willRemoved.setKey(largestOfLeft.getKey());
				reorder(largestOfLeft, null);
			}
		}else if(parent == null) {
			if(leftOfRemoved == null && rightOfRemoved == null) {
				willRemoved.setData(null);
				willRemoved.setKey(null);
			}else if(leftOfRemoved != null && rightOfRemoved == null) {
				transfer(willRemoved, leftOfRemoved);
			}else if(leftOfRemoved == null && rightOfRemoved != null) {
				transfer(willRemoved, rightOfRemoved);
			}else {
				BinarySearchTree<K, T> largestOfLeft = findLargest(leftOfRemoved);
				K storedKey = largestOfLeft.getKey();
				T storedData = largestOfLeft.getData();
				remove(storedKey);
				willRemoved.setKey(storedKey);
				willRemoved.setData(storedData);
			}
		}
	}
	
	public BinarySearchTree<K, T> getLeftSubtree() {
		return this.leftSubtree;
	}
	
	public BinarySearchTree<K, T> getRightSubtree() {
		return this.rightSubtree;
	}
	
	@SuppressWarnings("unchecked")
	private BinarySearchTree<K, T> getRoot(BinarySearchTree<K, T> tree){
		while(tree.getParent() != null) {
			tree = (BinarySearchTree<K, T>) tree.getParent();
		}
		return tree;
	}
	@Override
	public List<Tree<T>> getChildren() {
		List<Tree<T>> Children = new ArrayList<>();
		if(leftSubtree != null) {
			Children.add(leftSubtree);
		}
		if(rightSubtree != null) {
			Children.add(rightSubtree);			
		}
		if(Children.size() == 0) {
			return null;
		}
		
		return Children;
	}

	public BinarySearchTree<K, T> find(K key) {
		return findTree(getRoot(this), key);
	}
	
	private void addEntry(BinarySearchTree<K, T> root, K key, T data) {
		assert this.key != null;
		if(key.equals(root.key)) {
			root.setKey(key);
			root.setData(data);
		}else if(key.compareTo(root.key) < 0) {
			if(root.hasLeftSubtree()) {
				addEntry(root.getLeftSubtree(), key, data);
			}else {
				root.setLeftSubtree(new BinarySearchTree<>(key, data));
			}
		}else {
			assert key.compareTo(root.key) > 0;
			if(root.hasRightSubtree()) {
				addEntry(root.getRightSubtree(), key, data);
			}else {
				root.setRightSubtree(new BinarySearchTree<>(key, data));
			}
		}
	}
	
	private boolean hasLeftSubtree() {
		return leftSubtree != null;
	}
	private boolean hasRightSubtree() {
		return rightSubtree != null;
	}
	private void setLeftSubtree(BinarySearchTree<K, T> tree) {
		if(tree != null) {
			tree.setParent(this);
		}
		this.leftSubtree = tree;
	}
	
	private void setRightSubtree(BinarySearchTree<K, T> tree) {
		if(tree != null) {
			tree.setParent(this);
		}
		this.rightSubtree = tree;
	}
	
	private void transfer(BinarySearchTree<K, T> tree1, BinarySearchTree<K, T> tree2) {
		
		tree1.setData(tree2.getData());
		tree1.setKey(tree2.getKey());
		tree1.setLeftSubtree(tree2.getLeftSubtree());
		tree1.setRightSubtree(tree2.getRightSubtree());
	}
	
	private void reorder(BinarySearchTree<K, T> tree, BinarySearchTree<K, T> replaced) {
		@SuppressWarnings("unchecked")
		BinarySearchTree<K, T> parent = (BinarySearchTree<K, T>) tree.getParent();
		if(parent != null) {
			BinarySearchTree<K, T> parentLeft = parent.getLeftSubtree();
			BinarySearchTree<K, T> parentRight = parent.getRightSubtree();
			if(parentLeft != null && parentLeft.equals(tree)) {
				parent.setLeftSubtree(replaced);
			}else if(parentRight != null && parentRight.equals(tree)){
				parent.setRightSubtree(replaced);
			}
		}
	}	
	
	private BinarySearchTree<K, T> findLargest(BinarySearchTree<K, T> tree){
		if(tree.hasRightSubtree()) {
			tree = findLargest(tree.getRightSubtree());
		}
		return tree;
	}
	
	private BinarySearchTree<K, T> findTree(BinarySearchTree<K, T> root ,K key){
		BinarySearchTree<K, T> returned = null;
		if(!isNull(root)) {
			K rootKey = root.key;
			if(key.equals(rootKey)) {
				returned = root;
			}else if(key.compareTo(rootKey)< 0) {
				returned = findTree(root.getLeftSubtree(), key);
			}else {
				returned = findTree(root.getRightSubtree(), key);
			}
		}
		return returned;
	}
	
	private boolean isNull(BinarySearchTree<K, T> tree) {
		if(tree == null) {
			return true;
		}else if(tree.getKey() == null && tree.getData() == null && isNull(tree.getLeftSubtree()) && isNull(tree.getRightSubtree())){
			tree.setParent(null);
			return true;
		}else {
			return false;
		}
		
	}
}
