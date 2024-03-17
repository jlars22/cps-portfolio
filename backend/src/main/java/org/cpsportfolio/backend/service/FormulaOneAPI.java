package org.cpsportfolio.backend.service;

import org.springframework.http.ResponseEntity;

public interface FormulaOneAPI {
    ResponseEntity<String> getCurrentRaceCalendar();
}
