public interface Iterator<E> {

	boolean hasNext();

	E next();

	boolean hasPrevious();

	E previous();

	void remove();

	E currentItem();

}
