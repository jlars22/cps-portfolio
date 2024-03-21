package org.cpsportfolio.backend.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.iakovlev.timeshape.TimeZoneEngine;
import org.cpsportfolio.backend.external.WeatherAPI;
import org.cpsportfolio.backend.external.generated.weather.WeatherDataWrapper;
import org.cpsportfolio.backend.service.dto.racecalendar.RaceCalendarDto;
import org.cpsportfolio.backend.service.dto.trackweather.TrackWeatherDto;
import org.cpsportfolio.backend.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackWeatherService {

    private final WeatherAPI weatherAPI;
    private final RaceCalendarService raceCalendarService;

    public TrackWeatherDto getLiveWeather() {
        RaceCalendarDto nextRace = raceCalendarService.getNextRace();
        return mapToTrackWeatherDto(getLiveWeatherData(nextRace.getLat(), nextRace.getLon()));
    }

    private TrackWeatherDto mapToTrackWeatherDto(WeatherDataWrapper data) {
        ZonedDateTime localtime = getLocalTime(
            data.getLocation().getLat(),
            data.getLocation().getLon()
        );

        return new TrackWeatherDto(
            localtime.format(DateTimeFormatter.ofPattern("HH:mm")),
            localtime.getZone().toString(),
            data.getLocation().getLat(),
            data.getLocation().getLon(),
            data.getData().getValues().getTemperature(),
            data.getData().getValues().getWindSpeed(),
            data.getData().getValues().getVisibility(),
            data.getData().getValues().getHumidity(),
            data.getData().getValues().getRainIntensity(),
            data.getData().getValues().getCloudCover(),
            data.getData().getValues().getUvIndex(),
            data.getData().getValues().getWeatherCode()
        );
    }

    private WeatherDataWrapper getLiveWeatherData(double lat, double lon) {
        return JsonUtil.parseJson(weatherAPI.getLiveWeather(lat, lon), WeatherDataWrapper.class);
    }

    private ZonedDateTime getLocalTime(double lat, double lon) {
        TimeZoneEngine engine = TimeZoneEngine.initialize();
        Optional<ZoneId> zoneId = engine.query(lat, lon);

        return zoneId.map(ZonedDateTime::now).orElseGet(() -> ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
