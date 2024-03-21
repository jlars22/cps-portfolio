package org.cpsportfolio.backend.service.dto.trackweather;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackWeatherDto {

    String localTime;
    String location;
    double lat;
    double lon;
    double temperature;
    double windSpeed;
    double visibility;
    int humidity;
    int rainIntensity;
    int cloudCover;
    int uvIndex;
    int weatherCode;
}
