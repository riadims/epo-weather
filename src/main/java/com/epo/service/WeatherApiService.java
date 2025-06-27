package com.epo.service;

import com.epo.model.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApiService {
	private static final String API_KEY = "";
	private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

	public WeatherData getWeatherForCity(String city) throws Exception {
		String endpoint = String.format(URL, city, API_KEY);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(endpoint))
			.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.body());

		double temp = root.path("main").path("temp").asDouble();
		String desc = root.path("weather").get(0).path("description").asText();

		return new WeatherData(temp, desc);
	}
}
