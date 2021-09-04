public class Stack<E> {
	private Node<E> head;

	public void push(E e) {
		if (isEmpty()) {
			head = new Node<>(e, null);
		} else {
			head = new Node<>(e, head);
		}
	}

	public E pop() throws NullPointerException{
		if (isEmpty()) {
			throw new NullPointerException();
		} else {
			E e = head.elem;
			if (head.next != null) {
				head = head.next;
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
		Node<E> next;

		Node(E elem, Node<E> next) {
			this.elem = elem;
			this.next = next;
		}
	}

}
