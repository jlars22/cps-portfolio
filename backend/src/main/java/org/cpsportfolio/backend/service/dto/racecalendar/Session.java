package org.cpsportfolio.backend.service.dto.racecalendar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Session {

    String name;
    String date;
    Boolean nextUp;
}
