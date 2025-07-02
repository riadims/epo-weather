package com.epo.controller;

import com.epo.model.WeatherData;
import com.epo.service.WeatherApiService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.Map;

public class WeatherController {

	@FXML
	private ComboBox<String> citySelector;
	@FXML
	private Label resultLabel;
	@FXML
	private LineChart<Number, Number> tempChart;
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	private final WeatherApiService apiService = new WeatherApiService();

	private final Map<String, double[]> cityCoordinates = new HashMap<>() {{
		put("Maribor", new double[]{46.5547, 15.6459});
		put("Ljubljana", new double[]{46.0569, 14.5058});
		put("Koper", new double[]{45.5481, 13.7300});
	}};

	LocalTime now = LocalTime.now();
	int currentHour = now.getHour();

	@FXML
	public void initialize() {
		citySelector.getItems().addAll(cityCoordinates.keySet());

		tempChart.setAnimated(true);
		tempChart.setLegendVisible(true);

		xAxis.setLabel("Hour");
		xAxis.setAutoRanging(false);
		xAxis.setLowerBound(0);
		xAxis.setUpperBound(currentHour);
		xAxis.setTickUnit(1);
		xAxis.setMinorTickVisible(false);

		yAxis.setLabel("Temperature (°C)");
		yAxis.setAutoRanging(true);
	}


	@FXML
	private void handleGetWeather() {
		String selectedCity = citySelector.getValue();
		if (selectedCity == null) {
			resultLabel.setText("Please select a city.");
			return;
		}

		try {
			double[] coords = cityCoordinates.get(selectedCity);
			WeatherData data = apiService.getWeatherByCoords(coords[0], coords[1]);

			resultLabel.setText("Temperature: " + data.getTemp() + "°C\n" +
				"Condition: " + data.getDescription());

			XYChart.Series<Number, Number> series = new XYChart.Series<>();
			series.setName(selectedCity + " (simulated)");

			for (int hour = 0; hour < 24; hour++) {
				double mockTemp = data.getTemp() + Math.sin(hour / 3.0) * 2;
				series.getData().add(new XYChart.Data<>(hour, mockTemp));
			}

			tempChart.getData().clear();
			tempChart.getData().add(series);

		} catch (Exception e) {
			e.printStackTrace();
			resultLabel.setText("Error: " + e.getMessage());
			tempChart.getData().clear();
		}
	}
}
