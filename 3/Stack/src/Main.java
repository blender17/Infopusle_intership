public class Main {

	public static void main(String[] args) {
		Stack<Integer> integerStack = new Stack<>();
		integerStack.push(1);
		integerStack.push(2);
		integerStack.push(3);
		integerStack.push(4);
		integerStack.push(5);
		while (!integerStack.isEmpty()) {
			System.out.println(integerStack.pop());
		}

		ArrayStack<Integer> arrayStack = new ArrayStack<>();

		System.out.println("\n");

		for (int i = 0; i < 10; i++) {
			arrayStack.push(i);
		}

		while (!arrayStack.isEmpty()) {
			System.out.println(arrayStack.pop());
		}
	}

}
