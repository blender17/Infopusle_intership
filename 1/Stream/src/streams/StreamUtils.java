package streams;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamUtils {

	public static <T> Stream<T> firstN(Stream<T> stream, int num, Predicate<T> predicate) {
		return stream.filter(predicate).limit(num);
	}

}