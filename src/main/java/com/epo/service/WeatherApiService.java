package com.epo.service;

import com.epo.model.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Service class responsible for communicating with the OpenWeatherMap API
 * to retrieve current weather data for given geographical coordinates.
 */
public class WeatherApiService {

	private static final String API_KEY = "api key here";
	//Attain your free API key at: https://home.openweathermap.org/api_keys

	/**
	 * Fetches the current weather data for the given latitude and longitude.
	 *
	 * @param lat The latitude of the location.
	 * @param lon The longitude of the location.
	 * @return {@link WeatherData} object containing temperature and description.
	 * @throws Exception if the API request fails, or if the response is invalid.
	 */
	public WeatherData getWeatherByCoords(double lat, double lon) throws Exception {
		String endpoint = String.format(
			"https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s&units=metric",
			lat, lon, API_KEY
		);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(endpoint))
			.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() != 200) {
			throw new Exception("API error: " + response.statusCode());
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.body());

		JsonNode mainNode = root.path("main");
		JsonNode weatherArray = root.path("weather");

		if (mainNode.isMissingNode() || weatherArray.isMissingNode() || !weatherArray.isArray() || weatherArray.isEmpty()) {
			throw new Exception("Unexpected API response");
		}

		double temp = mainNode.path("temp").asDouble();
		String desc = weatherArray.get(0).path("description").asText();

		return new WeatherData(temp, desc);
	}
}
