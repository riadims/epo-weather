package com.epo.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherControllerTest {

	@Test
	public void testMockTemperatureGeneration() {
		WeatherController controller = new WeatherController();

		double baseTemp = 20.0;
		double[] temps = controller.generateMockTemperatures(baseTemp);

		assertEquals(24, temps.length, "There should be 24 temperature values.");

		for (int i = 0; i < 24; i++) {
			assertTrue(temps[i] >= 10 && temps[i] <= 30,
				"Temp at hour " + i + " is out of expected range: " + temps[i]);
		}
	}
}
