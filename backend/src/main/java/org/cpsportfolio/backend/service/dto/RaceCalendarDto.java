package org.cpsportfolio.backend.service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RaceCalendarDto {

    String name;
    String circuit;
    String date;
    String alpha2CountryCode;
    List<Session> sessions;
}
