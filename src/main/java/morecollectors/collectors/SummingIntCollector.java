package morecollectors.collectors;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

final class SummingIntCollector<T> implements UnorderedCollector<T, int[], Integer>
{
	private final ToIntFunction<? super T> mapper;
	
	SummingIntCollector(final ToIntFunction<? super T> mapper)
	{
		this.mapper = mapper;
	}

	@Override
	public BiConsumer<int[], T> accumulator()
	{
		return (array, element) -> {
			array[0] += mapper.applyAsInt(element);
		};
	}

	@Override
	public BinaryOperator<int[]> combiner()
	{
		return (array1, array2) -> new int[] { array1[0] + array2[0] };
	}

	@Override
	public Function<int[], Integer> finisher()
	{
		return array -> array[0];
	}

	@Override
	public Supplier<int[]> supplier()
	{
		return () -> new int[1];
	}
}
