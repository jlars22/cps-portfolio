package org.cpsportfolio.backend.controller;

import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.DriverStandingsService;
import org.cpsportfolio.backend.service.dto.driverstandings.DriverStandingsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/driver-standings")
@RequiredArgsConstructor
public class DriverStandingsController {

    private final DriverStandingsService driverStandingsService;

    @GetMapping
    public DriverStandingsDto getCurrentDriverStandings() {
        return driverStandingsService.getCurrentDriverStandings();
    }

    @GetMapping("/{round}")
    public DriverStandingsDto getDriverStandingsByRound(@PathVariable int round) {
        return driverStandingsService.getDriverStandingsByRound(round);
    }

    @GetMapping("/simple")
    public List<Map<String, Object>> getCurrentDriverStandingsSimple() {
        return driverStandingsService.getDriverStandingsSimple();
    }
}
