package com.blender.springshell.service;

import com.blender.springshell.currency.Converter;
import com.blender.springshell.currency.Currency;
import com.blender.springshell.currency.Statistic;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

	private final ObjectMapper objectMapper;
	private final Statistic statistic;
	private final Converter converter;
	private Set<Currency> currencies;

	@Autowired
	public CurrencyService(Statistic statistic, Converter converter) {
		this.statistic = statistic;
		this.converter = converter;
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		currencies = new HashSet<>();
		Currency uah = new Currency();
		uah.setCurrencyCode("UAH");
		uah.setCurrencyName("Гривня");
		currencies.add(uah);
	}

	public String convert(String cur1, String cur2, double amount , String date) {
		List<Currency> currencies = getExchangeRate(date);
		converter.setCurrencies(currencies);
		double result = converter.convert(cur1, cur2, amount);
		return amount + " " + cur1.toUpperCase() + " in " + cur2.toUpperCase() + " is " + result + " on " + currencies.get(0).getExchangeDate();
	}

	private List<Currency> getExchangeRate(String date) {
		LocalDate localDate = null;
		if (date.isEmpty()) {
			localDate = LocalDate.now();
		} else {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			localDate = LocalDate.parse(date.replaceAll("[-/]", "."), dateTimeFormatter);
		}

		LocalDate finalLocalDate = localDate;
		List<Currency> currencyList = currencies.stream()
				.filter(currency -> currency.getExchangeDate()
				.equals(finalLocalDate))
				.collect(Collectors.toList());

		if (currencyList.isEmpty()) {
			updateCurrencies(localDate);
			currencyList = currencies.stream()
					.filter(currency -> currency.getExchangeDate()
					.equals(finalLocalDate))
					.collect(Collectors.toList());
		}

		return currencyList;
	}

	private void updateCurrencies(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		try {
			List<Currency> currencyList = objectMapper
					.readValue(new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date="
							+ localDate.format(formatter)
							+ "&json"),
							new TypeReference<>() {});
			currencies.addAll(currencyList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStatistic(String cur, String startDate, String endDate) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate start = LocalDate.parse(startDate.replaceAll("[-/]", "."), dateTimeFormatter);
		LocalDate end;
		if (endDate.isEmpty()) {
			end = LocalDate.now();
		} else {
			end = LocalDate.parse(endDate.replaceAll("[-/]", "."), dateTimeFormatter).plusDays(1);
		}

		List<Currency> currencyList = new ArrayList<>((int) Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays());
		LocalDate pointer = start;

		while (pointer.isBefore(end)) {
			LocalDate finalPointer = pointer;
			Currency currency = currencies.stream()
					.filter(crr -> crr.getCurrencyName().equalsIgnoreCase(cur)
							&& crr.getExchangeDate().equals(finalPointer))
					.findAny()
					.orElse(null);
			if (currency == null) {
				updateCurrencies(pointer);
				currency = currencies.stream()
						.filter(crr -> crr.getCurrencyCode().equalsIgnoreCase(cur)
								&& crr.getExchangeDate().equals(finalPointer))
						.findAny()
						.orElse(null);
			}
			currencyList.add(currency);

			pointer = pointer.plusDays(1);
		}
		return buildStatisticString(currencyList);
	}

	private String buildStatisticString(List<Currency> currencyList) {
		statistic.setCurrencies(currencyList);
		String template = "%-17s%.3f\n%-17s%.3f\n%-17s%.3f\n%-17s%.3f\n%-17s%.3f";
		return String.format(template, "Min", statistic.min(), "Max", statistic.max(), "Mean",
				statistic.mean(), "Dispersion", statistic.dispersion(), "Std. Deviation", statistic.stdDeviation());
	}

	public String getCurrenciesList() {
		if (currencies != null && currencies.isEmpty()) {
			try {
				currencies = objectMapper.readValue(new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json")
						, new TypeReference<>() {});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuilder builder = new StringBuilder();
		assert currencies != null;
		for (Currency currency : currencies) {
			builder.append(currency.getCurrencyCode());
			builder.append(" ");
			builder.append(currency.getCurrencyName());
			builder.append("\n");
		}
		return builder.toString();
	}

}
