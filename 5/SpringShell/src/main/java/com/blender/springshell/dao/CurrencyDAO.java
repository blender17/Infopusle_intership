package com.blender.springshell.dao;

import com.blender.springshell.currency.Currency;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class CurrencyDAO {

    private final ObjectMapper mapper;
    private final Set<Currency> currencies;

    @Autowired
    public CurrencyDAO() {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        currencies = new HashSet<>();
    }

    public List<Currency> getCurrenciesByDate(LocalDate localDate) {
        List<Currency> currencyList = currencies.stream()
                .filter(currency -> currency.getExchangeDate()
                        .equals(localDate))
                .collect(Collectors.toList());

        if (currencyList.isEmpty()) {
            updateCurrencies(localDate);
            currencyList = currencies.stream()
                    .filter(currency -> currency.getExchangeDate()
                            .equals(localDate))
                    .collect(Collectors.toList());
        }
        return currencyList;
    }

    public List<Currency> getCurrenciesBetweenDate(LocalDate startDate, LocalDate endDate, String cur) {
        List<Currency> currencyList = new ArrayList<>((int) Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays());
        LocalDate pointer = startDate;

        while (pointer.isBefore(endDate)) {
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
        return currencyList;
    }

    private void updateCurrencies(LocalDate localDate) {
        Currency uah = new Currency();
        uah.setCurrencyCode("UAH");
        uah.setCurrencyName("Гривня");
        uah.setExchangeDate(localDate);
        currencies.add(uah);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            List<Currency> currencyList = mapper
                    .readValue(new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date="
                                    + localDate.format(formatter)
                                    + "&json"),
                            new TypeReference<>() {});
            currencies.addAll(currencyList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}