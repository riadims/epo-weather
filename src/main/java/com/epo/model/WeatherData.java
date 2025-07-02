package com.epo.model;

/**
 * Model class representing basic weather data.
 * Stores the current temperature and a short textual weather description.
 */
public class WeatherData {

	private final double temp;
	private final String description;

	/**
	 * Constructs a WeatherData object with the specified temperature and description.
	 *
	 * @param temp        The temperature in Celsius.
	 * @param description The weather condition description.
	 */
	public WeatherData(double temp, String description) {
		this.temp = temp;
		this.description = description;
	}

	/**
	 * Returns the temperature in Celsius.
	 *
	 * @return The temperature value.
	 */
	public double getTemp() {
		return temp;
	}

	/**
	 * Returns the weather description.
	 *
	 * @return The weather condition as a string.
	 */
	public String getDescription() {
		return description;
	}
}
