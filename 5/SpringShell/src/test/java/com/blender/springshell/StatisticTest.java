package com.blender.springshell;

import com.blender.springshell.currency.Currency;
import com.blender.springshell.currency.Statistic;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class StatisticTest {

	public static Statistic statistic;

	@BeforeClass
	public static void initialize(){
		Currency c1 = new Currency();
		c1.setExchangeRate(29.1);
		Currency c2 = new Currency();
		c2.setExchangeRate(25.6);
		Currency c3 = new Currency();
		c3.setExchangeRate(19.4);
		Currency c4 = new Currency();
		c4.setExchangeRate(23.2);
		Currency c5 = new Currency();
		c5.setExchangeRate(27.9);
		statistic = new Statistic(List.of(c1, c2, c3, c4, c5));
	}

	@Test
	public void testMin() {
		double expected = 19.4;
		double actual = statistic.min();
		Assert.assertEquals(expected, actual, 0.001);
		String template = "%-17s%.3f\n%-17s%.3f\n%-17s%.3f\n%-17s%.3f\n%-17s%.3f";
		System.out.println(String.format(template, "Min", statistic.min(), "Max", statistic.max(), "Mean",
				statistic.mean(), "Dispersion", statistic.dispersion(), "Std. Deviation", statistic.stdDeviation()));
	}

	@Test
	public void testMax() {
		double expected = 29.1;
		double actual = statistic.max();
		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void testAvg() {
		double expected = 25.04;
		double actual = statistic.mean();
		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void testDispersion() {
		double expected = 12.034;
		double actual = statistic.dispersion();
		Assert.assertEquals(expected, actual, 0.001);
	}

	@Test
	public void testStdDeviation() {
		double expected = 3.469;
		double actual = statistic.stdDeviation();
		Assert.assertEquals(expected, actual, 0.001);
	}
}