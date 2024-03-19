package org.cpsportfolio.backend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RaceCalendarDto {
    String name;
    String circuit;
    String date;
    String alpha2CountryCode;
    List<Session> sessions;
}
