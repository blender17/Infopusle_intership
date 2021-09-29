package com.blender.springshell.controller;

import com.blender.springshell.currency.Currency;
import com.blender.springshell.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@ShellComponent
public class ShellController {

	private final CurrencyService currencyService;

	private String cur1;
	private String cur2;
	private String date = "";
	private double amount;

	@Autowired
	public ShellController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@ShellMethod(key = "convert", value = "convert 20 [--from] USD [--to] UAH [--date] yyyy.MM.dd (/ or - also allowed)")
	public String convert(double amount, @ShellOption("--from")  String cur1, @ShellOption("--to")
			String cur2, @ShellOption(value = "--date", defaultValue = "")  String date) {
		double result = currencyService.convert(cur1, cur2, amount, date);
		return amount + " " + cur1.toUpperCase() + " in " + cur2.toUpperCase() + " is " + result + " on " +
				(date.isEmpty() ? LocalDate.now().toString() : date) ;
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
		double result = currencyService.convert(cur1, cur2, amount, date);
		return amount + " " + cur1.toUpperCase() + " in " + cur2.toUpperCase() + " is " + result + " on " +
				(date.isEmpty() ? LocalDate.now().toString() : date);
	}

	@ShellMethod(key = "statistic", value = "calculates statistic on currency between --start and --end dates yyyy.MM.dd (/ or - also allowed)")
	public String statistic(String cur, @ShellOption("--start")  String startDate, @ShellOption(value = "--end", defaultValue = "") String endDate) {
		String end = endDate.isEmpty() ? LocalDate.now().toString() : endDate;
		String template = "%-17s%.3f\n%-17s%.3f\n%-17s%.3f\n%-17s%.3f\n%-17s%.3f";
		Map<String, Double> statistics = currencyService.getStatistic(cur, startDate, endDate);
		return cur.toUpperCase() + " statistic between " + startDate + " and " + end + "\n" +
				String.format(template, "Min", statistics.get("min"), "Max", statistics.get("max"), "Mean",
				statistics.get("mean"), "Dispersion", statistics.get("dispersion"), "Std. Deviation", statistics.get("std.deviation"));
	}

	@ShellMethod(key = "currencies", value = "Print list of currency codes and names")
	public String currencies() {
		List<Currency> currencies = currencyService.getCurrenciesList();
		StringBuilder builder = new StringBuilder();
		for (Currency currency : currencies) {
			builder.append(currency.getCurrencyCode());
			builder.append(" ");
			builder.append(currency.getCurrencyName());
			builder.append("\n");
		}
		return builder.toString();
	}

	@ShellMethod(key = "close", value = "Close the application")
	public void close() {
		System.exit(0);
	}
}
