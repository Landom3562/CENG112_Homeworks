package tree;

import java.util.List;
import exception.HaveToBeImplementedException;

public class GenericTree<T> extends Tree<T> {
	
	public GenericTree(T data) {
		setData(data);
	}

	public void addChild(GenericTree<T> child) {
		throw new HaveToBeImplementedException("GenericTree.addChild(Tree<T> child)");
	}
	
	public void removeChild(GenericTree<T> child) {
		throw new HaveToBeImplementedException("GenericTree.removeChild(Tree<T> child)");
	}
	
	@Override
	public List<Tree<T>> getChildren() {
		throw new HaveToBeImplementedException("GenericTree.getChildren()");
	}
}
