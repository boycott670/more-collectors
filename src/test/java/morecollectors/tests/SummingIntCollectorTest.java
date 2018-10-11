package morecollectors.tests;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

import morecollectors.collectors.MoreCollectors;

public class SummingIntCollectorTest
{
	@Test
	public void test()
	{
		assertEquals(1_000 * 1_000_000, IntStream.generate(() -> 1_000).limit(1_000_000).parallel().boxed().collect(MoreCollectors.summingInt(Integer::intValue)).intValue());
	}
}
