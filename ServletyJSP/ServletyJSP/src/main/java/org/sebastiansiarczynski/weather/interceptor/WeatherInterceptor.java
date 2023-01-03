package org.sebastiansiarczynski.weather.interceptor;


import static spark.Spark.before;
import static spark.Spark.halt;

import java.util.Map;
import org.eclipse.jetty.util.ajax.JSON;

/**
 * Klasa, która wykonuje walidację zapytania HTTP zanim przejdzie do naszego kontrolera.
 *
 * @author Sebastian Siarczyński
 */
public class WeatherInterceptor {

  private final static String WEATHER_API_HEADER = "X-Weather-Api-Key";

  /**
   * Metoda wykonująca walidację zapytania HTTP
   */
  public void intercept() {
    before("/*", (req, res) -> {
      String headers = req.headers(WEATHER_API_HEADER);

      if (headers == null || headers.isBlank()) {
        halt(400, JSON.toString(Map.of("message", "Lack of weather api key header!")));
      }

      System.out.printf("Request for path: %s is valid", req.uri());
    });
  }
}
