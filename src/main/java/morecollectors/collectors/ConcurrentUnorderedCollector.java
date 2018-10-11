package morecollectors.collectors;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface ConcurrentUnorderedCollector<T, A, R> extends ConcurrentCollector<T, A, R>, UnorderedCollector<T, A, R>
{
	@Override
	default Set<Characteristics> characteristics()
	{
		return Collections.unmodifiableSet(
			Stream.of(ConcurrentCollector.super.characteristics(), UnorderedCollector.super.characteristics())
				.flatMap(Collection::stream)
				.collect(Collectors.toSet())
		);
	}
}
