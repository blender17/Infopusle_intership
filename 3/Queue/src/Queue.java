public class Queue<E> {
	private Node<E> head;
	private Node<E> tail;

	public void push(E e) {
		if (isEmpty()) {
			tail = new Node<>(e, null);
			head = tail;
			head.prev = tail;
		} else {
			Node<E> newElem = new Node<>(e, null);
			tail.prev = newElem;
			tail = newElem;
		}
	}

	public E pop() throws NullPointerException{
		if (isEmpty()) {
			throw new NullPointerException();
		} else {
			E e = head.elem;
			if (head.prev != null) {
				head = head.prev;
			}
			else {
				head = null;
			}
			return e;
		}
	}

	public boolean isEmpty() {
		return head == null;
	}

	private static class Node<E> {
		E elem;
		Node<E> prev;

		Node(E elem, Node<E> prev) {
			this.elem = elem;
			this.prev = prev;
		}
	}

}
