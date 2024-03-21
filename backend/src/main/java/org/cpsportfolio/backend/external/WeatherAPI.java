package org.cpsportfolio.backend.external;

public interface WeatherAPI {
    String getLiveWeather(double lat, double lon);
}
