package morecollectors.collectors;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

final class SummingIntCollector<T> implements ConcurrentUnorderedCollector<T, AtomicInteger, Integer>
{
	private final ToIntFunction<? super T> mapper;
	
	SummingIntCollector(final ToIntFunction<? super T> mapper)
	{
		this.mapper = mapper;
	}

	@Override
	public BiConsumer<AtomicInteger, T> accumulator()
	{
		return (accumulation, inputElement) -> accumulation.getAndAccumulate(mapper.applyAsInt(inputElement), Integer::sum);
	}

	@Override
	public BinaryOperator<AtomicInteger> combiner()
	{
		return (accumulation1, accumulation2) -> new AtomicInteger(accumulation1.get() + accumulation2.get());
	}

	@Override
	public Function<AtomicInteger, Integer> finisher()
	{
		return AtomicInteger::get;
	}

	@Override
	public Supplier<AtomicInteger> supplier()
	{
		return AtomicInteger::new;
	}
}
