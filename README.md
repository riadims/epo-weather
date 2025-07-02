# EPO Weather App

This is a simple desktop weather application built for the "Elektronsko poslovanje" course (UNIT 21). It allows users to select a city and view real-time weather data, including a temperature chart, using JavaFX and the OpenWeatherMap API.

## Technologies Used

- Java 17
- JavaFX (FXML, Charts)
- Maven
- OpenWeatherMap API
- JUnit 5 (for unit testing)
- Jackson (for JSON parsing)

---

## Features

- Selectable cities (Maribor, Ljubljana, Koper)
- Real-time weather display (temperature + condition)
- LineChart visualization of 24-hour simulated temperature trend
- Manual dependency injection
- Unit tested API service logic

---

## Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/epo-weather.git
   cd epo-weather

Open the project in IntelliJ or another IDE with Maven support.
Add your OpenWeatherMap API key to WeatherApiService.java:

private static final String API_KEY = "YOUR_KEY_HERE";

You can attain your free API key here: https://home.openweathermap.org/api_keys

Run the app: mvn clean javafx:run
To run tests: mvn test
