package org.cpsportfolio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.TrackWeatherService;
import org.cpsportfolio.backend.service.dto.trackweather.TrackWeatherDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class TrackWeatherController {

    private final TrackWeatherService trackWeatherService;

    @GetMapping("/live")
    public TrackWeatherDto getLiveWeather() {
        return trackWeatherService.getLiveWeather();
    }
}
