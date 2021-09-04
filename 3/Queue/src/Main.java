public class Main {

	public static void main(String[] args) {
		Queue<Integer> integerQueue = new Queue<>();
		integerQueue.push(1);
		integerQueue.push(2);
		integerQueue.push(3);
		integerQueue.push(4);
		integerQueue.push(5);
		while (!integerQueue.isEmpty()) {
			System.out.println(integerQueue.pop());
		}
	}

}
