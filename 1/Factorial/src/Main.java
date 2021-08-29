import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {

		BigInteger bigIntResult;
		int result;
		long startTime;
		long endTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 1000; i ++) {
			factorialCycle(16);
		}
		endTime = System.nanoTime();

		result = factorialCycle(16);
		System.out.println("Cycle result: " + result);
		System.out.println("Cycle performance: " + (endTime - startTime)  + " ns");

		startTime = System.nanoTime();
		for (int i = 0; i < 1000; i ++) {
			factorialRecursion(16);
		}
		endTime = System.nanoTime();

		result = factorialRecursion(16);
		System.out.println("Recursion result: " + result);
		System.out.println("Recursion performance: " + (endTime - startTime) + " ns");

		System.out.println("BIG INTEGER FACTORIAL");

		startTime = System.nanoTime();
		bigIntResult = bigIntFactorialCycle(22000); //10k slowest, 1m time 211 seconds
		endTime = System.nanoTime();

//		System.out.println("Cycle result: " + bigIntResult);
		System.out.println("Cycle performance: " + (endTime - startTime)  + " ns");

		startTime = System.nanoTime();
		try {
			bigIntResult = bigIntFactorialRecursion(22000); //10k fastest
		} catch (StackOverflowError error) {
			System.out.println("overflow");
		}
		endTime = System.nanoTime();

//		System.out.println("Recursion result: " + bigIntResult);
		System.out.println("Recursion performance: " + (endTime - startTime) + " ns");


	}


	public static int factorialRecursion(int n) {
		if (n >= 1) {
			return n * factorialRecursion(n - 1);
		} else {
			return 1;
		}
	}

	public static int factorialCycle(int n) {
		if ((n == 1) || (n == 0)) {
			return 1;
		}

		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static BigInteger bigIntFactorialRecursion(int n) {
		if (n >= 1) {
			return BigInteger.valueOf(n).multiply(bigIntFactorialRecursion(n - 1));
		} else {
			return BigInteger.ONE;
		}
	}

	public static BigInteger bigIntFactorialCycle(int n) {

		if ((n == 1) || (n == 0)) {
			return BigInteger.ONE;
		}

		BigInteger result = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			result = BigInteger.valueOf(i).multiply(result);
		}
		return result;
	}

}
