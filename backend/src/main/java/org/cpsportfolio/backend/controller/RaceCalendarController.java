package org.cpsportfolio.backend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.RaceCalendarService;
import org.cpsportfolio.backend.service.dto.racecalendar.RaceCalendarDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class RaceCalendarController {

    private final RaceCalendarService raceCalendarService;

    @GetMapping
    public List<RaceCalendarDto> getRaceCalendar() {
        return raceCalendarService.getCurrentRaceCalendar();
    }

    @GetMapping("/next")
    public RaceCalendarDto getNextRace() {
        return raceCalendarService.getNextRace();
    }
}
