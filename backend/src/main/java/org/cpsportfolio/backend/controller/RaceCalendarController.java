package org.cpsportfolio.backend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.RaceCalendarService;
import org.cpsportfolio.backend.service.dto.RaceCalendarDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RaceCalendarController {

    private final RaceCalendarService raceCalendarService;

    @GetMapping("/raceCalendar")
    public List<RaceCalendarDto> getRaceCalendar() throws Exception {
        return raceCalendarService.getCurrentRaceCalendar();
    }
}
