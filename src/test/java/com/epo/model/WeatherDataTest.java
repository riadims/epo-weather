package com.epo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherDataTest {

	@Test
	public void testModelFields() {
		WeatherData data = new WeatherData(25.0, "cisto");

		assertEquals(25.0, data.getTemp());
		assertEquals("cisto", data.getDescription());
	}
}
