package com.blender.springshell.service;

import com.blender.springshell.currency.Currency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class CurrencyService {

	private final ObjectMapper objectMapper;
	private List<Currency> currencies;

	public CurrencyService() {
		objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
	}

	public List<Currency> getExchangeRate() {
		try {
			currencies = objectMapper.readValue(new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json")
					, new TypeReference<>() {});

			Currency uah = new Currency();
			uah.setCurrencyCode("UAH");
			uah.setCurrencyName("Гривня");
			currencies.add(uah);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currencies;
	}

	public List<Currency> getExchangeRate(String date) {
		String formatterDate = date.replaceAll("[-./]", "");
		try {
			currencies = objectMapper.readValue(new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date="
					+ formatterDate + "&json"), new TypeReference<>() {});

			Currency uah = new Currency();
			uah.setCurrencyCode("UAH");
			uah.setCurrencyName("Гривня");
			currencies.add(uah);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currencies;
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
		for (Currency currency : currencies) {
			builder.append(currency.getCurrencyCode());
			builder.append(" ");
			builder.append(currency.getCurrencyName());
			builder.append("\n");
		}
		return builder.toString();
	}

}
