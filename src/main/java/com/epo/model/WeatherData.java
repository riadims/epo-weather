package com.epo.model;

/**
 * Model class for Weather Data.
 */
public class WeatherData {
	private final double temp;
	private final String description;

	public WeatherData(double temp, String description) {
		this.temp = temp;
		this.description = description;
	}

	public double getTemp() {
		return temp;
	}

	public String getDescription() {
		return description;
	}
}
