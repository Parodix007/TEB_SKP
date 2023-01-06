# Weather App

* Aplikacja pokazowa na zajęcia w TEB SKP Java Programista I.
* Aplikacja prezentuje aplikację prostego API napisanego w Spark Java.

## Obsługa
* Aby włączyc aplikację wystarczy uruchomic metodę main
* Aplikacja powinna pokazac następujące logi: 
  ```java
    Successfully mounted routes! 
    Successfully mounted /weather endpoints!
* Aby sprawdzic poprawnośc dzialania aplikacji należy wykonac w aplikacji Postman lub terminalu 
  następujące zapytania HTTP:
  ```http request
    GET /weather/location?lat=51.89&lon=-8.46 HTTP/1.1
    Host: localhost:8081
    X-Weather-Api-Key: TUTAJ WSTAW SWÓJ KLUCZ API OPEN WEATHER
  
    GET /weather/city/london HTTP/1.1
    Host: localhost:8081
    X-Weather-Api-Key: TUTAJ WSTAW SWÓJ KLUCZ API OPEN WEATHER