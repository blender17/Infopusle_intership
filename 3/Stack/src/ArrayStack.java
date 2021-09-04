import java.util.Arrays;

public class ArrayStack<E> {

	private static final int DEFAULT_CAPACITY = 8;
	private E[] stack;
	private int head;
	private int capacity;

	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayStack(int initialCapacity) {
		head = -1;
		if (initialCapacity < 0) {
			capacity = DEFAULT_CAPACITY;
		} else {
			capacity = initialCapacity;
		}
		stack = (E[]) new Object[capacity];
	}

	private void increaseCapacity() {
		capacity = stack.length << 1;
		stack = Arrays.copyOf(stack, capacity);
	}

	public void push(E e) {
		if ((capacity - 1) == head) {
			increaseCapacity();
		}
		head++;
		stack[head] = e;
	}

	public E pop() throws NullPointerException{
		if (isEmpty()) {
			throw new NullPointerException();
		}
		E e = stack[head];
		head--;
		return e;
	}

	public boolean isEmpty() {
		return head < 0;
	}


}
