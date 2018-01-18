package de.lutz.task.exchange.fixerio;

import java.net.URL;
import java.time.LocalDate;

import org.junit.Test;

import static org.junit.Assert.*;

public class FixerIoUrlBuilderTest {
	
	@Test
	public void testBuildWithoutSpecification() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		URL url = builder.build();
		assertEquals("https://api.fixer.io/latest", url.toString());
	}
	
	@Test
	public void testBuildWithDate() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		builder.setDate(LocalDate.of(2018, 1, 18));
		URL url = builder.build();
		assertEquals("https://api.fixer.io/2018-01-18", url.toString());
	}
	
	@Test
	public void testBuildWithBase() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		builder.setBase("EUR");
		URL url = builder.build();
		assertEquals("https://api.fixer.io/latest?base=EUR;", url.toString());
	}
	
	@Test
	public void testBuildWithSingleSymbol() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		builder.addSymbol("GBP");
		URL url = builder.build();
		assertEquals("https://api.fixer.io/latest?symbols=GBP;", url.toString());
	}

	@Test
	public void testBuildWithMultipleSymbols() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		builder.addSymbol("GBP");
		builder.addSymbol("USD");
		URL url = builder.build();
		assertEquals("https://api.fixer.io/latest?symbols=GBP,USD;", url.toString());
	}

	@Test
	public void testBuildWithBaseAndSingleSymbol() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		builder.setBase("EUR");
		builder.addSymbol("GBP");
		URL url = builder.build();
		assertEquals("https://api.fixer.io/latest?base=EUR;symbols=GBP;", url.toString());
	}

	@Test
	public void testBuildWithBaseAndMultipleSymbols() {
		FixerIoUrlBuilder builder = new FixerIoUrlBuilder();
		builder.setBase("EUR");
		builder.addSymbol("GBP");
		builder.addSymbol("USD");
		URL url = builder.build();
		assertEquals("https://api.fixer.io/latest?base=EUR;symbols=GBP,USD;", url.toString());
	}
}
