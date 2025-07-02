package com.epo.service;

import com.epo.model.WeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherApiServiceTest {

	private final WeatherApiService service = new WeatherApiService();

	@Test
	public void testGetWeatherForValidCoords() throws Exception {
		double lat = 46.0569;
		double lon = 14.5058;

		WeatherData data = service.getWeatherByCoords(lat, lon);
		assertNotNull(data);
		assertTrue(data.getTemp() > -50 && data.getTemp() < 60);
		assertNotNull(data.getDescription());
	}

	@org.junit.Test
	public void testGetWeatherWithInvalidCoords() {
		double lat = -999;
		double lon = -999;

		Exception exception = assertThrows(Exception.class, () -> {
			service.getWeatherByCoords(lat, lon);
		});

		String message = exception.getMessage();
		assertTrue(message.contains("API error") || message.contains("Unexpected"));
	}
}
