package org.sebastiansiarczynski.weather.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;

/**
 * Klasa będąca obiektem transferu danych (DTO) odpowiedzialnym za przesyłanie danych informacji
 * zwrotnej do klienta. Klasa ta mapuje pola z informacji zwrontej z
 * <a href="https://openweathermap.org/current">API Open Weather</a>
 *
 * @author Sebastian Siarczyński
 */
public class WeatherResponseDTO {

  private final static ObjectMapper objectMapper = new ObjectMapper();
  private final WeatherResponse[] weather;
  private final String name;
  private final TempResponse main;
  private final String error;

  private final LocalDateTime localDateTime = LocalDateTime.now();

  public static final class WeatherResponse {

    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
      return id;
    }

    public String getDescription() {
      return description;
    }

    public String getMain() {
      return main;
    }

    public String getIcon() {
      return icon;
    }

  }

  public static final class TempResponse {

    private int temp;
    private int pressure;
    private int humidity;
    private int temp_min;
    private int temp_max;
    private int feels_like;
    private int sea_level;
    private int grnd_level;

    public int getTemp() {
      return temp;
    }

    public int getPressure() {
      return pressure;
    }

    public int getHumidity() {
      return humidity;
    }

    public int getTemp_min() {
      return temp_min;
    }

    public int getTemp_max() {
      return temp_max;
    }

    public int getFeels_like() {
      return feels_like;
    }

    public int getSea_level() {
      return sea_level;
    }

    public int getGrnd_level() {
      return grnd_level;
    }
  }

  private WeatherResponseDTO(final WeatherResponse[] weather,
      final TempResponse main, final String name, final String error) {
    this.weather = weather;
    this.name = name;
    this.main = main;
    this.error = error;
  }

  /**
   * Metoda fabryczna tworząca klasę na podstawie JsonNode, który jest otrzymywany z odpowiedzi z
   * Open Weather
   *
   * @param jsonNode JSON z odpowiedzi od Open Weather
   * @return Nowy obiekt DTO
   */
  public static WeatherResponseDTO fromJsonNode(final JsonNode jsonNode) {
    WeatherResponse[] weathers = objectMapper.convertValue(jsonNode.get("weather"),
        WeatherResponse[].class);

    TempResponse main = objectMapper.convertValue(jsonNode.get("main"), TempResponse.class);
    String name = objectMapper.convertValue(jsonNode.get("name"), String.class);

    return new WeatherResponseDTO(weathers, main, name, "");
  }

  /**
   * Metoda fabryczna tworząca klasę na podstawie błędu jaki wystąpił podczas pobierania danych z
   * Open Weather
   *
   * @param error Wiadomośc z błędem
   * @return Nowy obiekt DTO
   */
  public static WeatherResponseDTO fromError(final String error) {
    return new WeatherResponseDTO(null, null, null, error);
  }

  public WeatherResponse[] getWeather() {
    return weather;
  }

  public String getName() {
    return name;
  }

  public TempResponse getMain() {
    return main;
  }

  public String getError() {
    return error;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }
}
