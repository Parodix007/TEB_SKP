package org.sebastiansiarczynski.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.sebastiansiarczynski.weather.dto.WeatherResponseDTO;
import org.sebastiansiarczynski.weather.dto.WeatherResponseDTO.WeatherResponse;

/**
 * Klasa, w której jest wykonywana logika stojąca za pobieraniem pogody
 *
 * @author Sebastian Siarczyński
 */
public class WeatherService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final String OPEN_WEATHER_ROOT_URL = "https://api.openweathermap.org/data/2.5/";

  public WeatherResponseDTO getWeatherDataForCity(final String city, final String weatherToken)
      throws URISyntaxException, IOException, InterruptedException {
    final String url = String.format("weather?q=%s&appid=%s", city, weatherToken);

    final HttpClient httpClient = HttpClient.newHttpClient();
    final HttpRequest request = HttpRequest.newBuilder(new URI(OPEN_WEATHER_ROOT_URL + url))
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

    JsonNode jsonNode = objectMapper.readTree(response.body());

    return WeatherResponseDTO.fromJsonNode(jsonNode);
  }
}
