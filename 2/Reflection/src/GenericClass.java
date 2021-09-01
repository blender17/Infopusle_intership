import java.util.List;

public class GenericClass<T> {

	private List<T> list;

	public GenericClass() {}

	public List<T> get() {
		return list;
	}

	public void set(List<? extends T> list) {
		this.list = (List<T>) list;
	}

}
