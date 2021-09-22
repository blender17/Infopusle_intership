package com.blender.springshell.currency;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {

	private List<Currency> currencies;

	public Converter() {}

	public Converter(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public double convert(String cur1, String cur2, double amount) {
		Currency currency1 = currencies.stream()
				.filter(currency -> currency.getCurrencyCode().equalsIgnoreCase(cur1))
				.findFirst()
				.orElseThrow();
		Currency currency2 = currencies.stream()
				.filter(currency -> currency.getCurrencyCode().equalsIgnoreCase(cur2))
				.findFirst()
				.orElseThrow();

		return convert(currency1, currency2, amount);
	}

	public double convert(Currency cur1, Currency cur2, double amount) {
		double result = 0;
		if (cur1.getCurrencyCode().equalsIgnoreCase("UAH") || cur2.getCurrencyCode().equalsIgnoreCase("UAH")) {
			result = uahConvert(cur1, cur2, amount);
		}
		else {
			result = crossConvert(cur1, cur2, amount);
		}
		return result;
	}

	private double uahConvert(Currency cur1, Currency cur2, double amount) {
		double result = 0;
		if (cur1.getCurrencyCode().equalsIgnoreCase("UAH")) {
			result = amount / cur2.getExchangeRate();
		} else if (cur2.getCurrencyCode().equalsIgnoreCase("UAH")) {
			result = amount * cur1.getExchangeRate();
		}

		return result;
	}

	private double crossConvert(Currency cur1, Currency cur2, double amount) {
		return amount * (cur1.getExchangeRate() / cur2.getExchangeRate());
	}
}
