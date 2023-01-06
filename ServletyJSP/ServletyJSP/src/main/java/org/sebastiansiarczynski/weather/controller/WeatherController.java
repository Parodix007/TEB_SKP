package org.sebastiansiarczynski.weather.controller;

import static spark.Spark.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.eclipse.jetty.util.ajax.JSON;
import org.sebastiansiarczynski.weather.dto.WeatherResponseDTO;
import org.sebastiansiarczynski.weather.service.WeatherService;

/**
 * Klasa, która jest odpowiedzialna za tworzenie ścieżek pod ścieżką nadrzędna
 * <b>/weather</b> oraz obsługę odpowiedzi do użytkownika.
 *
 * @author Sebastian Siarczyński
 */
public class WeatherController {

  private final WeatherService weatherService;
  private final static String WEATHER_API_HEADER = "X-Weather-Api-Key";
  private final ObjectMapper objectMapper = new ObjectMapper();

  public WeatherController(final WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  /**
   * Metoda odpowiedzialna za tworzenie i obsługę ścieżek.
   */
  public void routesInit() {
    get("/city/:city", (req, res) -> {
      String city = req.params("city");

      try {
        WeatherResponseDTO weatherDataForCity = weatherService.getWeatherDataForCity(city,
            req.headers(WEATHER_API_HEADER));

        res.status(200);
        res.body(
            objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(weatherDataForCity));
      } catch (final Exception e) {
        e.printStackTrace();

        res.status(500);
        res.body(JSON.toString(Map.of("message", e.getMessage())));
      }

      res.type("application-json");
      return res.body();
    });

    get("/location", (req, res) -> {
      res.type("application-json");

      String lat = req.queryParams("lat");
      String lon = req.queryParams("lon");

      if (lat == null || lon == null) {
        res.status(400);
        res.body(JSON.toString(Map.of("message", "Missing query params!")));

        return res.body();
      }

      try {
        WeatherResponseDTO weatherDataForLocation = weatherService.getWeatherDataForLocation(lat,
            lon, req.headers(WEATHER_API_HEADER));

        res.status(200);
        res.body(objectMapper.writerWithDefaultPrettyPrinter()
            .writeValueAsString(weatherDataForLocation));
      } catch (final Exception e) {
        e.printStackTrace();

        res.status(500);
        res.body(JSON.toString(Map.of("message", e.getMessage())));
      }

      return res.body();
    });

    System.out.println("Successfully mounted routes!");
  }
}
