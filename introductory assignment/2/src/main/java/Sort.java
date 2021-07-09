import java.util.Random;

public class Sort {

	Random random = new Random(System.nanoTime());

	public void quickSort(int[] array, int start, int end) {
		if (start < end) {
			int p = randomisedPartition(array, start, end);
			quickSort(array, start, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private int partition(int[] array, int start, int end) {
		int pivot = array[end];
		int i = start - 1;
		for (int j = start; j <= end - 1; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, end);
		return ++i;
	}

	private int randomisedPartition(int[] array, int start, int end) {
		int i = random.nextInt((end + 1) - start) + start;
		swap(array, i, end);
		return partition(array, start, end);
	}

	private void swap(int[] array, int i, int j) {
		//works only for a + b <= Integer.MAX_VALUE
		//array[i] = array[i] + array[j] - (array[j] = array[i]);

		//works in situation where a = Integer.MAX_VALUE and b = Integer.MAX_VALUE situation but uses additional variable
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public void quickSort(double[] array, int start, int end) {
		if (start < end) {
			int p = randomisedPartition(array, start, end);
			quickSort(array, start, p - 1);
			quickSort(array, p + 1, end);
		}
	}

	private int partition(double[] array, int start, int end) {
		double pivot = array[end];
		int i = start - 1;
		for (int j = start; j <= end - 1; j++) {
			if (array[j] <= pivot) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, end);
		return ++i;
	}

	private int randomisedPartition(double[] array, int start, int end) {
		int i = random.nextInt((end + 1) - start) + start;
		swap(array, i, end);
		return partition(array, start, end);
	}

	private void swap(double[] array, int i, int j) {
		//works only for a + b <= Integer.MAX_VALUE
		//array[i] = array[i] + array[j] - (array[j] = array[i]);

		//works in situation where a = Integer.MAX_VALUE and b = Integer.MAX_VALUE situation but uses additional variable
		double tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

}
