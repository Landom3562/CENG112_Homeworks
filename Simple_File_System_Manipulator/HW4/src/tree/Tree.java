package tree;
import java.util.List;
import java.util.function.Consumer;

import exception.HaveToBeImplementedException;

public abstract class Tree<T> {	
	public abstract List<Tree<T>> getChildren();

	public T getData() {
		throw new HaveToBeImplementedException("Tree.getData()");
	}
	
	protected void setData(T data) {
		throw new HaveToBeImplementedException("Tree.setData(T data)");
	}
	
	public Tree<T> getParent() {
		throw new HaveToBeImplementedException("Tree.getParent()");
	}
	
	protected void setParent(Tree<T> tree) {
		throw new HaveToBeImplementedException("Tree.setParent(Tree<T> tree)");
	}
		
	public boolean isLeaf() {
		return getChildren() == null;
	}
	
	public int getNumberOfNodes() {
		throw new HaveToBeImplementedException("Tree.getNumberOfNodes()");

	}
	public int getHeight() {
		throw new HaveToBeImplementedException("Tree.getHeight()");
	}
	
	public void traverseLevelOrder(Consumer<T> visitingMethod) {
		throw new HaveToBeImplementedException("Tree.traverseLevelOrder(Consumer<T> visitingMethod)");
	}
	
	public void traversePreOrderRecursively(Consumer<T> visitingMethod) {
		throw new HaveToBeImplementedException("Tree.traversePreOrderRecursively(Consumer<T> visitingMethod)");
	}
	
	public void traversePostOrderIteratively(Consumer<T> visitingMethod) {
		throw new HaveToBeImplementedException("Tree.traversePostOrderIteratively(Consumer<T> visitingMethod)");
	}
}
