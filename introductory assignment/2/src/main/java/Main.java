import java.util.Arrays;
import java.util.Random;

public class Main {

	private static final int arrSize = 30;

	public static void main(String[] args) {

		Random random = new Random(System.currentTimeMillis());

		int[] array = Arrays.stream(new int[arrSize])
				.map(elem -> elem = random.nextInt(100) + 1)
				.toArray();

		System.out.println("Initial array:");
		printArray(array);

		Sort sort = new Sort();
		sort.quickSort(array, 0, array.length - 1);

		System.out.println("Sorted array:");
		printArray(array);

		double[] doubleArray = Arrays.stream(new double[arrSize])
				.map(elem -> elem = (double) Math.round(Math.random() * 1000) / 1000)
				.toArray();

		System.out.println("Initial array:");
		printArray(doubleArray);

		sort.quickSort(doubleArray, 0, doubleArray.length - 1);

		System.out.println("Sorted array:");
		printArray(doubleArray);

	}

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length - 1) {
				System.out.print(", ");
			} else {
				System.out.println(".");
			}
			if ((i + 1) % 10 == 0) {
				System.out.println();
			}
		}
	}

	public static void printArray(double[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length - 1) {
				System.out.print(", ");
			} else {
				System.out.println(".");
			}
			if ((i + 1) % 10 == 0) {
				System.out.println();
			}
		}
	}

}
