package org.sebastiansiarczynski.weather.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;

public class WeatherResponseDTO implements Serializable {

  private final static ObjectMapper objectMapper = new ObjectMapper();
  private final WeatherResponse[] weather;
  private final String name;
  private final TempResponse main;

  public static final class WeatherResponse implements Serializable {

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

    public void setId(int id) {
      this.id = id;
    }

    public void setMain(String main) {
      this.main = main;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }
  }

  public static final class TempResponse implements Serializable {

    private int temp;
    private int pressure;
    private int humidity;
    private int temp_min;
    private int temp_max;

    private long feels_like;

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

    public long getFeels_like() {
      return feels_like;
    }

    public void setTemp(int temp) {
      this.temp = temp;
    }

    public void setPressure(int pressure) {
      this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
      this.humidity = humidity;
    }

    public void setTemp_min(int temp_min) {
      this.temp_min = temp_min;
    }

    public void setTemp_max(int temp_max) {
      this.temp_max = temp_max;
    }

    public void setFeels_like(long feels_like) {
      this.feels_like = feels_like;
    }
  }

  private WeatherResponseDTO(final WeatherResponse[] weather,
      final TempResponse main, final String name) {
    this.weather = weather;
    this.name = name;
    this.main = main;
  }

  public static WeatherResponseDTO fromJsonNode(final JsonNode jsonNode) throws IOException {
    WeatherResponse[] weathers = objectMapper.convertValue(jsonNode.get("weather"),
        WeatherResponse[].class);

    TempResponse main = objectMapper.convertValue(jsonNode.get("main"), TempResponse.class);
    String name = objectMapper.convertValue(jsonNode.get("name"), String.class);

    return new WeatherResponseDTO(weathers, main, name);
  }
}

