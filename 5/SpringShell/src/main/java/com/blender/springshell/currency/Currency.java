package com.blender.springshell.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@JsonIgnoreProperties("r030")
public class Currency {

	@JsonProperty("cc")
	private String currencyCode;
	@JsonProperty("rate")
	private double exchangeRate;
	@JsonProperty("exchangedate")
	private LocalDate exchangeDate;
	@JsonProperty("txt")
	private String currencyName;

	@JsonSetter("exchangedate")
	public void setExchangeDate(String date) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		this.exchangeDate = LocalDate.parse(date, dateTimeFormatter);
	}

	public void setExchangeDate(LocalDate exchangeDate) {
		this.exchangeDate = exchangeDate;
	}
}
