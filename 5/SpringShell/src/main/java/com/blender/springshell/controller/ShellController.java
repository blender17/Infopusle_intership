package com.blender.springshell.controller;

import com.blender.springshell.currency.Converter;
import com.blender.springshell.currency.Currency;
import com.blender.springshell.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class ShellController {

	private final CurrencyService currencyService;
	private final Converter converter;

	private String cur1;
	private String cur2;
	private String date = "";
	private double amount;

	@Autowired
	public ShellController(CurrencyService currencyService, Converter converter) {
		this.currencyService = currencyService;
		this.converter = converter;
	}

	@ShellMethod(key = "convert", value = "convert 20 [--from] USD [--to] UAH [--date] yyyy.MM.dd (/ or - also allowed)")
	public String convert(double amount, @ShellOption("--from")  String cur1, @ShellOption("--to")
			String cur2, @ShellOption(value = "--date", defaultValue = "")  String date) {

		List<Currency> currencies = null;
		if (date.isEmpty()) {
			currencies = currencyService.getExchangeRate();
		} else {
			currencies = currencyService.getExchangeRate(date);
		}
		converter.setCurrencies(currencies);
		double result = converter.convert(cur1, cur2, amount);
		return amount + " " + cur1.toUpperCase() + " in " + cur2.toUpperCase() + " is " + result + " on " + currencies.get(0).getExchangeDate();
	}

	@ShellMethod(key = "from", value = "currency code that converting from")
	public void setFrom(String cur1) {
		this.cur1 = cur1.toUpperCase();
	}

	@ShellMethod(key = "to", value = "target currency code")
	public void setTarget(String cur2) {
		this.cur2 = cur2.toUpperCase();
	}

	@ShellMethod(key = "amount", value = "amount of currency")
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@ShellMethod(key = "date", value = "exchange rate date yyyy.MM.dd (/ or - also allowed)")
	public void setDate(String date) {
		this.date = date;
	}

	@ShellMethod(key = "convertp", value = "pre-configured convert")
	public String convert() {
		List<Currency> currencies = null;
		if (date.isEmpty()) {
			currencies = currencyService.getExchangeRate();
		} else {
			currencies = currencyService.getExchangeRate(date);
		}
		converter.setCurrencies(currencies);
		double result = converter.convert(cur1, cur2, amount);
		return amount + " " + cur1 + " in " + cur2 + " is " + result + " on " + currencies.get(0).getExchangeDate();
	}

	@ShellMethod(key = "currencies", value = "Print list of currency codes and names")
	public String getCurrency() {
		List<Currency> currencies = currencyService.getExchangeRate();
		return currencyService.getCurrenciesList();
	}

	@ShellMethod(key = "close", value = "Close the application")
	public void close() {
		System.exit(0);
	}

}
