import java.util.Arrays;

public class ArrayQueue<E> {

	private static final int DEFAULT_CAPACITY = 8;

	private E[] queue;
	private int capacity;
	private final static int HEAD = 0;
	private int tail;

	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayQueue(int initialCapacity) {
		tail = 0;
		if (initialCapacity < 0) {
			capacity = DEFAULT_CAPACITY;
		} else {
			capacity = initialCapacity;
		}
		queue = (E[]) new Object[capacity];
	}

	private void increaseCapacity() {
		capacity = queue.length << 1;
		queue = Arrays.copyOf(queue, capacity);
	}

	public void push(E e) {
		if (capacity == tail) {
			increaseCapacity();
		}
		queue[tail] = e;
		tail++;
	}

	public E pop() throws NullPointerException{
		if (isEmpty()) {
			throw new NullPointerException();
		}
		E e = queue[HEAD];
		for (int i = 0; i < tail - 1; i++) {
			queue[i] = queue[i + 1];
		}
		tail--;
		return e;
	}

	public boolean isEmpty() {
		return HEAD == tail;
	}

}
