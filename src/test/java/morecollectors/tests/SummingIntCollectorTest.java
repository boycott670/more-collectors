package morecollectors.tests;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Test;

import morecollectors.collectors.MoreCollectors;

public class SummingIntCollectorTest
{
	@Test
	public void test()
	{
		assertEquals(0, Stream.empty().collect(MoreCollectors.summingInt(Object::hashCode)).intValue());
	}
}
