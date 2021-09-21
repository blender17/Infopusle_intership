package com.blender.springshell;

import com.blender.springshell.currency.Converter;
import com.blender.springshell.currency.Currency;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class ConverterTest {

	private static Converter converter;

	@BeforeClass
	public static void initialize() {
		Currency uah = new Currency();
		uah.setCurrencyCode("UAH");

		Currency usd = new Currency();
		usd.setCurrencyCode("USD");
		usd.setExchangeRate(26.7);

		Currency azn = new Currency();
		azn.setCurrencyCode("AZN");
		azn.setExchangeRate(15.8333);

		Currency xau = new Currency();
		xau.setCurrencyCode("XAU");
		xau.setExchangeRate(47005.21);

		converter = new Converter(List.of(uah, usd, azn, xau));
	}


	@Test
	public void usdToUah() {
		double expected = 1148.1;

		double actual = converter.convert("usd", "uah", 43);
		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void uahToUsd() {
		double expected = 25.1310861423;

		double actual = converter.convert("uah", "USD", 671);
		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void aznToXau() {
		double expected = 29.150374735055965072807886615122;

		double actual = converter.convert("aZn", "XaU", 86542);
		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void xauToUsd() {
		Currency usd = new Currency();
		usd.setCurrencyCode("USD");
		usd.setExchangeRate(26.7);
		Currency xau = new Currency();
		xau.setCurrencyCode("XAU");
		xau.setExchangeRate(47005.21);

		double expected = 2147.8036029962546816479400749064;

		double actual = converter.convert(xau, usd, 1.22);
		Assert.assertEquals(expected, actual, 0.001);
	}

}