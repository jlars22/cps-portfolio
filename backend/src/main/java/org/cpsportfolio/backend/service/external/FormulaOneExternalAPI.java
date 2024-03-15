package org.cpsportfolio.backend.service.external;

import org.springframework.http.ResponseEntity;

public interface FormulaOneExternalAPI {
    ResponseEntity<String> getCurrentRaceCalendar();
}
