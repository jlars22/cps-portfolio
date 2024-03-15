package org.cpsportfolio.backend.service.dto;

import java.util.List;

public record RaceCalendarDto(
        String name, String circuit, String formattedDate, String alpha2CountryCode, List<Session> sessions) {}
