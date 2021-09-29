package com.blender.springshell.service;

import com.blender.springshell.currency.Converter;
import com.blender.springshell.currency.Currency;
import com.blender.springshell.currency.Statistic;
import com.blender.springshell.dao.CurrencyDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

	private final ObjectMapper objectMapper;
	private final Statistic statistic;
	private final Converter converter;
	private final CurrencyDAO currencyDAO;

	@Autowired
	public CurrencyService(Statistic statistic, Converter converter, CurrencyDAO currencyDAO) {
		this.statistic = statistic;
		this.converter = converter;
		this.currencyDAO = currencyDAO;
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
	}

	public double convert(String cur1, String cur2, double amount , String date) {
		List<Currency> currencies = getExchangeRate(date);
		converter.setCurrencies(currencies);
		return converter.convert(cur1, cur2, amount);
	}

	private List<Currency> getExchangeRate(String date) {
		LocalDate localDate = null;
		if (date.isEmpty()) {
			localDate = LocalDate.now();
		} else {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
			localDate = LocalDate.parse(date.replaceAll("[-/]", "."), dateTimeFormatter);
		}
		return currencyDAO.getCurrenciesByDate(localDate);
	}

	public Map<String, Double> getStatistic(String cur, String startDate, String endDate) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDate start = LocalDate.parse(startDate.replaceAll("[-/]", "."), dateTimeFormatter);
		LocalDate end;
		if (endDate.isEmpty()) {
			end = LocalDate.now();
		} else {
			end = LocalDate.parse(endDate.replaceAll("[-/]", "."), dateTimeFormatter).plusDays(1);
		}
		return buildStatisticMap(currencyDAO.getCurrenciesBetweenDate(start, end, cur));
	}

	private Map<String, Double> buildStatisticMap(List<Currency> currencies) {
		Map<String, Double> statistics = new HashMap<>();
		statistic.setCurrencies(currencies);
		statistics.put("min", statistic.min());
		statistics.put("max", statistic.max());
		statistics.put("mean", statistic.mean());
		statistics.put("dispersion", statistic.dispersion());
		statistics.put("std.deviation", statistic.stdDeviation());
		return statistics;
	}

	public List<Currency> getCurrenciesList() {
		return currencyDAO.getCurrenciesByDate(LocalDate.now());
	}

}
