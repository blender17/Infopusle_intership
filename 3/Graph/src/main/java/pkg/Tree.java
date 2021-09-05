package pkg;

public class Tree<E> {
	private E value;
	private Tree<E> left;
	private Tree<E> right;

	public Tree(E value, Tree<E> left, Tree<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Tree(E value) {
		this(value, null, null);
	}

	public E dfs(E find) {
		E found = null;
		if (value.equals(find)) {
			return value;
		} else {
			if (left != null) {
				E tmp = left.dfs(find);
				if (tmp != null) {
					found = tmp;
				}
			}

			if (right != null) {
				E tmp = right.dfs(find);
				if (tmp != null) {
					found = tmp;
				}
			}
		}
		return found;
	}

	public E bfs(E find, Tree<E> root) {
		Queue<Tree<E>> queue = new Queue<>();
		queue.push(root);

		while (!queue.isEmpty()) {
			Tree<E> node = queue.pop();
			if (node.value.equals(find)) {
				return node.value;
			} else {
				if (node.left != null) {
					queue.push(node.left);
				}
				if (node.right != null) {
					queue.push(node.right);
				}
			}
		}
		return null;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public Tree<E> getLeft() {
		return left;
	}

	public void setLeft(Tree<E> left) {
		this.left = left;
	}

	public Tree<E> getRight() {
		return right;
	}

	public void setRight(Tree<E> right) {
		this.right = right;
	}
}
