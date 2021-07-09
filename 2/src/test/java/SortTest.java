import org.junit.Assert;
import org.junit.Test;

public class SortTest {

	private Sort sort = new Sort();

	@Test
	public void intSort() {
		int[] array = {18, 36, 8, 25, 6, 5, 40, 54, 63, 83, 38};
		int[] expectedArray = {5, 6, 8, 18, 25, 36, 38, 40, 54, 63, 83};

		sort.quickSort(array, 0, array.length - 1);

		Assert.assertArrayEquals(array, expectedArray);
	}

	@Test
	public void doubleSort() {
		double[] doubleArray = {0.07, 0.01, 0.49, 0.59, 0.85, 0.66, 0.42, 0.83, 0.28, 0.92};

		sort.quickSort(doubleArray, 0, doubleArray.length - 1);

		for (int i = 0; i < doubleArray.length - 1; i++) {
			Assert.assertTrue(doubleArray[i] <= doubleArray[i + 1]);
		}
	}
}
