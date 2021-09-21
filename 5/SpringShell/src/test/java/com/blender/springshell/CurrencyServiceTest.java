package com.blender.springshell;

import com.blender.springshell.currency.Currency;
import com.blender.springshell.service.CurrencyService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class CurrencyServiceTest {

	private static CurrencyService currencyService;

	@BeforeClass
	public static void initialize() {
		currencyService = new CurrencyService();
	}

	@Test
	public void getExchangeRate() {
		List<Currency> currencies = currencyService.getExchangeRate();
		Assert.assertFalse(currencies.isEmpty());
		Assert.assertTrue(currencies.stream().anyMatch(currency -> currency.getCurrencyCode().equalsIgnoreCase("SGD")));
	}


	@Test
	public void getExchangeRateByDate() {
		List<Currency> currencies = currencyService.getExchangeRate("2021.09.21");
		Currency currency = currencies.stream().filter(currency1 -> currency1.getCurrencyCode().equalsIgnoreCase("USD")).findFirst().orElseThrow();
		String expectedCCY = "USD";
		double expectedRate = 26.7072;
		LocalDate expectedDate = LocalDate.of(2021, 9, 21);

		Assert.assertEquals(expectedCCY, currency.getCurrencyCode());
		Assert.assertEquals(expectedDate, currency.getExchangeDate());
		Assert.assertEquals(expectedRate, currency.getExchangeRate(), 0);
	}
}