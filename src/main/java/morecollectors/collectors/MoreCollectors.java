package morecollectors.collectors;

import java.util.function.ToIntFunction;
import java.util.stream.Collector;

public final class MoreCollectors
{
	public static <T> Collector<T, ?, Integer> summingInt(final ToIntFunction<? super T> mapper)
	{
		return new SummingIntCollector<>(mapper);
	}
}
