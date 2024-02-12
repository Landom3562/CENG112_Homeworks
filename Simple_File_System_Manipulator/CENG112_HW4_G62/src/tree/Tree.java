package tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.function.Consumer;


public abstract class Tree<T> {	
	public abstract List<Tree<T>> getChildren();
	
	private T data;
	private Tree<T> Parent;
	
	
	public T getData() {
		return data;
	}
	
	protected void setData(T data) {
		this.data = data;
	}
	
	public Tree<T> getParent() {
		return Parent;
	}
	
	protected void setParent(Tree<T> tree) {
		this.Parent = tree;
	}
		
	public boolean isLeaf() {
		return getChildren() == null;
	}
	
	public int getNumberOfNodes() {
		return getNumberOfNodes(this);

	}
	public int getHeight() {
		return getHeight(this);
	}
	
	public void traverseLevelOrder(Consumer<T> visitingMethod) {
		Queue<Tree<T>> queue = new LinkedList<>();
		queue.add(this);
		while(!queue.isEmpty()) {
			Tree<T> front = queue.remove();
			visitingMethod.accept(front.getData());
			for(Tree<T> child: front.getChildren()) {
				queue.add(child);
			}
		}
	}
	
	public void traversePreOrderRecursively(Consumer<T> visitingMethod) {
		traversePreOrderRecursively(this, visitingMethod);
	}
	
	public void traversePostOrderIteratively(Consumer<T> visitingMethod) {
		Stack<Tree<T>> postOrderStack = getPostOrderStack(this);
		while(!postOrderStack.isEmpty()) {
			visitingMethod.accept(postOrderStack.pop().getData());
		}
	}
	private int getHeight(Tree<T> tree) {
		int height = 0;
		
		if(tree != null) {
			List<Integer> heights = new ArrayList<Integer>();
			if(tree.getChildren() != null) {
				for(Tree<T> subtree: tree.getChildren()) {
					heights.add(getHeight(subtree));
				}
			}
			height = 1 + getMax(heights);
		}
		return height;
	}
	
	private int getMax(List<Integer> list) {
		int max = 0;
		for(int elem:list) {
			if(elem > max) {
				max = elem;
			}
		}
		return max;
	}
	private int getNumberOfNodes(Tree<T> tree) {
		List<Integer> subNodeCount = new ArrayList<Integer>();
		if(tree.getChildren() != null) {
			for(Tree<T> subtree: tree.getChildren()) {
				subNodeCount.add(getNumberOfNodes(subtree));
			}
		}
		return 1 + sumOf(subNodeCount);
	}
	
	private int sumOf(List<Integer> list) {
		int sum = 0;
		for(int i : list) {
			sum += i;
		}
		return sum;
	}
	private void traversePreOrderRecursively(Tree<T> tree, Consumer<T> visitingMethod) {
		if(tree != null) {
			visitingMethod.accept(tree.getData());
			for(Tree<T> child : tree.getChildren()) {
				traversePreOrderRecursively(child, visitingMethod);
			}
		}
	}
	private Stack<Tree<T>> getPostOrderStack(Tree<T> tree){
		Stack<Tree<T>> inputStack = new Stack<>();
		Stack<Tree<T>> outputStack = new Stack<>();
		inputStack.push(tree);
		while(!inputStack.isEmpty()) {
			Tree<T> topmost = inputStack.pop();
			outputStack.push(topmost);
			for(Tree<T> child : topmost.getChildren()) {
				inputStack.push(child);
			}
		}
		return outputStack;
	}
}
