package org.cpsportfolio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.DriverStandingsService;
import org.cpsportfolio.backend.service.dto.driverstandings.DriverStandingsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/driver-standings")
@RequiredArgsConstructor
public class DriverStandingsController {

    private final DriverStandingsService driverStandingsService;

    @GetMapping
    public DriverStandingsDto getCurrentDriverStandings() {
        return driverStandingsService.getCurrentDriverStandings();
    }
}
