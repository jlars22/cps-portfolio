package org.cpsportfolio.backend.external.implementations;

import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.config.AppConfig;
import org.cpsportfolio.backend.external.WeatherAPI;
import org.cpsportfolio.backend.util.HttpClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TomorrowioAPI implements WeatherAPI {

    private final HttpClient httpClient;
    private final AppConfig config;
    private final String baseUrl = "https://api.tomorrow.io/v4/weather/realtime";

    @Override
    public String getLiveWeather(double lat, double lon) {
        String formattedUrl =
            baseUrl + "?location=" + lat + "," + lon + "&apikey=" + config.getWeatherApiKey();
        return httpClient.doGetRequest(formattedUrl).getBody();
    }
}
