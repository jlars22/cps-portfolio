package org.cpsportfolio.backend.service.dto;

public record RaceCalendarDto(
        String name, String circuit, String formattedDate, String alpha2CountryCode, SessionDto[] sessions) {}
