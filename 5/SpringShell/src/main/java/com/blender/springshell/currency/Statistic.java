package com.blender.springshell.currency;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {

	private List<Currency> currencies;


	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public double min() {
		return currencies.stream()
				.mapToDouble(Currency::getExchangeRate)
				.min()
				.orElse(0);
	}

	public double max() {
		return currencies.stream()
				.mapToDouble(Currency::getExchangeRate)
				.max()
				.orElse(0);
	}

	public double mean() {
		return currencies.stream()
				.mapToDouble(Currency::getExchangeRate)
				.average()
				.orElse(0);
	}

	public double dispersion() {
		double mean = mean();
		double temp = 0;
		for (Currency currency : currencies) {
			temp += Math.pow((currency.getExchangeRate() - mean), 2);
		}
		return  temp / currencies.size();
	}

	public double stdDeviation() {
		return Math.sqrt(dispersion());
	}



}
