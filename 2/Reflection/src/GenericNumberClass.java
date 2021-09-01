import java.util.List;

public class GenericNumberClass<T extends Number> extends GenericClass<T>{

	private List<T> list;

	protected int x;

	private GenericNumberClass() {}

	public GenericNumberClass(List<? extends T> list) {
		this.list = (List<T>) list;
	}

	public boolean union(List<? super T> consumer, List<? extends T> producer, int i) {
		return consumer.addAll(producer);
	}
}
