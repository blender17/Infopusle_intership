package com.blender.springshell.currency;

import java.util.List;
import java.util.stream.Collectors;

public class StringParser {

	public static String[] parse(String data, List<Currency> currencies) {
		List<String> currencyCodes = currencies.stream().map(Currency::getCurrencyCode).collect(Collectors.toList());
		String[] values = null;
		String[] stringsArray = data.split("\\s");
		if (stringsArray[stringsArray.length - 1].matches("([0-3]\\d[-./]){2}\\d{4}")) {
			values = new String[4];
			values[values.length - 1] = stringsArray[stringsArray.length -1];
		} else {
			values = new String[3];
		}
		for (int i = 0; i < values.length - 1;) {
			for (String str : stringsArray) {
				if (str.matches("^\\d+(\\.\\d+)?")) {
					values[i] = str;
					i++;
				} else if (currencyCodes.contains(str.toUpperCase())) {
					values[i] = str;
					i++;
				}
			}
		}

		return values;
	}

}
