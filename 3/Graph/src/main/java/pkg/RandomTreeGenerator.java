package pkg;

import java.util.Random;

public class RandomTreeGenerator {

	private final Random random;

	public RandomTreeGenerator() {
		random = new Random(System.nanoTime());
	}

	public Tree<Integer> generate(int n) {
		if (n == 0) {
			return null;
		}
		Tree<Integer> root = new Tree<>(random.nextInt(10 + 1));

		int nodeNum = random.nextInt(n);
		root.setLeft(generate(nodeNum));
		root.setLeft(generate(nodeNum));

		return root;
	}
}
