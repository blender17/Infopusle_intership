package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import static streams.StreamUtils.firstN;

public class Main {

	public static void main(String[] args) {
		firstN(Stream.of("asc", "abc", "bac", "abs", "desc", "abba", "ab", "ab", "ac", "ab"),
				2, s -> s.contains("ab")).forEach(System.out::println);

		firstN(Stream.of(1, 2, 3, 4, 5, 6, 7, 8 ,9, 10, 11 ,12 ,13 ,14, 15),
				4, n -> n%2 == 0).sorted(Comparator.reverseOrder()).forEach(System.out::println);

		int[] fib = {0, 1, 1 ,2 ,3 ,5, 8, 13, 21, 34, 55};

		Arrays.stream(fib).skip(3).mapToDouble(Math::sqrt).reduce((a, b) -> b).ifPresent(System.out::println);

	}

}
