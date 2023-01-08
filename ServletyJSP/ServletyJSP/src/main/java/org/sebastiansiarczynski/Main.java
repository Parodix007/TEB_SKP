package org.sebastiansiarczynski;

import static spark.Spark.notFound;
import static spark.Spark.path;
import static spark.Spark.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;
import java.util.Map;
import org.eclipse.jetty.util.ajax.JSON;
import org.sebastiansiarczynski.weather.controller.WeatherController;
import org.sebastiansiarczynski.weather.interceptor.WeatherInterceptor;
import org.sebastiansiarczynski.weather.service.WeatherService;

public class Main {

  /**
   * Wejściowe miejsce w programie - tutaj są tworzone potrzebne klasy do poprawnego działania
   * programu oraz konfiguracja API Sparkowego, czyli ustawiany jest port, nadżędna ścieżka oraz
   * obsługa błędu 404 Not Found
   *
   * @param args argumenty systemowe
   */
  public static void main(String[] args) {
    port(8081);

    final WeatherService weatherService = new WeatherService(HttpClient.newHttpClient(),
        new ObjectMapper());
    final WeatherInterceptor weatherInterceptor = new WeatherInterceptor();
    final WeatherController weatherController = new WeatherController(weatherService);

    path("/weather", () -> {
      weatherInterceptor.intercept();
      weatherController.routesInit();

      System.out.println("Successfully mounted /weather endpoints!");
    });

    notFound((req, res) -> {
      res.type("application/json");
      res.body(JSON.toString(Map.of("message",
          String.format("Path %s doesn't exists!", req.uri()))));

      return res.body();
    });
  }
}
