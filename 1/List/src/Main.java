import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		Iterator<Integer> iterator = list.iterator();

		iterator.remove();
		iterator.remove();

		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}

		DoublyLinkedList<String> stringList = new DoublyLinkedList<>();
		stringList.add("e");
		stringList.add("n");
		stringList.add("o");
		stringList.add("d");

		stringList.forEach(System.out::print);
		System.out.println();

		Iterator<String> stringIterator = stringList.descendingIterator();
		for (int i = 0; i < stringList.size(); i++) {
			System.out.print(stringIterator.next());
		}


	}

}
