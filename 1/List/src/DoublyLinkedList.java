import java.util.function.Consumer;

public class DoublyLinkedList<E>{

	private final Node<E> header;
	private int size;

	public DoublyLinkedList() {
		size = 0;
		header = new Node<>(null, null, null);
		header.setPrev(header);
		header.setNext(header);
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public DLLIterator iterator () {
		return new DLLIterator(header.getNext(), false, size);
	}

	public DLLIterator descendingIterator() {
		return new DLLIterator(header.getPrev(), true, size);
	}

	public void add(E e) {
		Node<E> node;
		if (isEmpty()) {
			node = new Node<>(e, header, header);
			header.setNext(node);
		} else {
			node = new Node<>(e, header.getPrev(), header);
			header.getPrev().setNext(node);
		}
		header.setPrev(node);
		size++;
	}

	//Inserts the specified element at the specified position in this list.
	//Shifts the element currently at that position and any subsequent elements to the right.
	public void add(int index, E e) throws IndexOutOfBoundsException {
		if ((index >= size()) && (index < 0)) {
			throw new  IndexOutOfBoundsException();
		}

		Node<E> currentNode = header.getNext();
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNext();
		}
		Node<E> newNode = new Node<>(e, currentNode.getPrev(), currentNode);
		currentNode.getPrev().setNext(newNode);
		currentNode.setPrev(newNode);
		size++;
	}

	public E getFirst () {
		return header.getNext().get();
	}

	public E getLast() {
		return header.getPrev().get();
	}

	public E get(int index) throws IndexOutOfBoundsException {
		if ((index >= size()) && (index < 0)) {
			throw new  IndexOutOfBoundsException();
		}

		if (index == 0) {
			return getFirst();
		}

		if (index == (size - 1)) {
			return getLast();
		}

		Node<E> node = header.getNext();
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}

		return node.get();
	}

	public E remove(int index) throws IndexOutOfBoundsException {
		if ((index >= size()) && (index < 0)) {
			throw new  IndexOutOfBoundsException();
		}

		Node<E> node = header.getNext();
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}

		unlink(node);
		size--;

		return node.get();
	}

	public E set(int index, E e) throws IndexOutOfBoundsException {
		if ((index >= size()) && (index < 0)) {
			throw new  IndexOutOfBoundsException();
		}

		Node<E> node = header.getNext();
		for (int i = 0; i < index; i++) {
			node = node.getNext();
		}
		node.set(e);
		return node.get();
	}

	public void forEach(Consumer<E> action) {
		for (Iterator<E> i = iterator(); i.hasNext();) {
			action.accept(i.next());
		}
	}

	private static <T> void unlink(Node<T> node) {
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());
		node.set(null);
		node.setPrev(null);
		node.setNext(null);
	}



	private static class Node<E> {

		private E elem;
		private Node<E> prev;
		private Node<E> next;

		Node(E elem, Node<E> prev, Node<E> next) {
			this.elem = elem;
			this.prev = prev;
			this.next = next;
		}

		E get() {
			return elem;
		}

		void set(E e) {
			this.elem = e;
		}

		Node<E> getNext() {
			return next;
		}

		Node<E> getPrev() {
			return prev;
		}

		void setNext(Node<E> next) {
			this.next = next;
		}

		void setPrev(Node<E> prev) {
			this.prev = prev;
		}
	}



	private class DLLIterator implements Iterator<E> {

		private final boolean desc;
		private Node<E> position;
		private int size;
		private int index;


		DLLIterator(Node<E> position, boolean desc, int size) {
			this.desc = desc;
			this.position = position;
			this.size = size;
			if (desc) {
				index = size - 1;
			} else {
				index = 0;
			}
		}

		public boolean hasNext() {
			if (desc) {
				return hasPrevious();
			}
			return index < size;
		}

		public E next() {
			if (desc) {
				return previous();
			}
			E current = position.get();
			position = position.getNext();
			index++;
			return current;
		}

		public boolean hasPrevious() {
			return index > 0;
		}

		public E previous() {
			E current = position.get();
			position = position.getPrev();
			index--;
			return current;
		}


		//removes current element and moves pointer
		public void remove() throws NullPointerException {
			if (size > 0){
				Node<E> next;
				if (desc) {
					next = position.getPrev();
				} else {
					next = position.getNext();
				}

				unlink(position);
				position = next;
				size--;
			} else {
				throw new NullPointerException();
			}
		}

		@Override
		public E currentItem() {
			return position.get();
		}
	}
}