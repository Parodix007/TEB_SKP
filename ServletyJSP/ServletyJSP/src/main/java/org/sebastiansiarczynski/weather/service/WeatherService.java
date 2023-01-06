package org.sebastiansiarczynski.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.sebastiansiarczynski.weather.dto.WeatherResponseDTO;

/**
 * Klasa, w której jest wykonywana logika stojąca za pobieraniem pogody
 *
 * @author Sebastian Siarczyński
 */
public class WeatherService {

  private static final String OPEN_WEATHER_ROOT_URL = "https://api.openweathermap.org/data/2.5/";
  private final ObjectMapper objectMapper;
  private final HttpClient httpClient;

  public WeatherService(final HttpClient httpClient, final ObjectMapper objectMapper) {
    this.httpClient = httpClient;
    this.objectMapper = objectMapper;
  }

  /**
   * Metoda odpowiedzialna za pobieranie pogody dla konkretnego miasta
   *
   * @param city         Nazwa miasta, dla którego pobrana ma byc pogoda
   * @param weatherToken Token do API Open Weather
   * @return Obiekt {@link WeatherResponseDTO} z pogodą dla miasta
   */
  public WeatherResponseDTO getWeatherDataForCity(final String city, final String weatherToken) {
    try {
      final String url = String.format("weather?q=%s&appid=%s", city, weatherToken);

      final HttpRequest request = HttpRequest.newBuilder(new URI(OPEN_WEATHER_ROOT_URL + url))
          .build();

      return getWeatherResponseDTO(request);
    } catch (final Exception e) {
      throw new RuntimeException("Error while getting weather data!" + e.getMessage(), e);
    }
  }

  /**
   * Metoda odpowiedzialna za pobranie pogody dla konkretnej lokalizacji
   *
   * @param latitude     Szerokośc geograficzna
   * @param longitude    Długośc geograficzna
   * @param weatherToken Token do API Open Weather
   * @return Obiekt {@link WeatherResponseDTO} z pogoda dla zadanej szerokości oraz długości
   * geograficznej
   */
  public WeatherResponseDTO getWeatherDataForLocation(final String latitude,
      final String longitude, final String weatherToken) {
    try {
      final String url = String.format("weather?lat=%s&lon=%s&appid=%s", latitude, longitude,
          weatherToken);

      HttpRequest request = HttpRequest.newBuilder(new URI(OPEN_WEATHER_ROOT_URL + url)).build();

      return getWeatherResponseDTO(request);
    } catch (final Exception e) {
      throw new RuntimeException("Error while getting weather data!" + e.getMessage(), e);
    }
  }

  private WeatherResponseDTO getWeatherResponseDTO(HttpRequest request)
      throws IOException, InterruptedException {
    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      return WeatherResponseDTO.fromError(response.body());
    }

    JsonNode jsonNode = objectMapper.readTree(response.body());

    return WeatherResponseDTO.fromJsonNode(jsonNode);
  }
}
