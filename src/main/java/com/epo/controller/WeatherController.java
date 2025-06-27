package com.epo.controller;

import com.epo.model.WeatherData;
import com.epo.service.WeatherApiService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WeatherController {
	@FXML
	private TextField cityField;
	@FXML
	private Label resultLabel;

	private final WeatherApiService apiService = new WeatherApiService();

	@FXML
	private void handleGetWeather() {
		String city = cityField.getText().trim();
		if (city.isEmpty()) {
			resultLabel.setText("Please enter a city.");
			return;
		}

		try {
			WeatherData data = apiService.getWeatherForCity(city);
			resultLabel.setText("Temperature: " + data.getTemp() + "°C\n" +
				"Condition: " + data.getDescription());
		} catch (Exception e) {
			e.printStackTrace(); // Add this line
			resultLabel.setText("Error: " + e.getMessage());
		}

	}
}
