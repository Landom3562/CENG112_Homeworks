package tree;

import java.util.ArrayList;
import java.util.List;

public class GenericTree<T> extends Tree<T> {
	
	private List<Tree<T>> Children = new ArrayList<>();
	
	public GenericTree(T data) {
		setData(data);
	}

	public void addChild(GenericTree<T> child) {
		Children.add(child);
		child.setParent(this);
	}
	
	public void removeChild(GenericTree<T> child) {
		Children.remove(child);
		child.setParent(null);
	}
	
	@Override
	public List<Tree<T>> getChildren() {
		return Children;
	}
}
