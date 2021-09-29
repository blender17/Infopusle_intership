package com.blender.springshell.controller;

import com.blender.springshell.currency.Currency;
import com.blender.springshell.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private final CurrencyService currencyService;

	@Autowired
	public RestController(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	@GetMapping("/convert")
	public double convert(@RequestParam double amount, @RequestParam String ccy1, @RequestParam String ccy2, @RequestParam(required = false) Optional<String> date) {
		return currencyService.convert(ccy1, ccy2, amount, date.orElse(""));
	}

	@GetMapping("/statistic")
	public Map<String, Double> convert(@RequestParam String ccy, @RequestParam String startDate, @RequestParam(required = false) Optional<String> endDate) {
		return currencyService.getStatistic(ccy, startDate, endDate.orElse(""));
	}

	@GetMapping("/currencies")
	public List<Currency> convert() {
		return currencyService.getCurrenciesList();
	}

}
