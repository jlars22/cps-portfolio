package org.cpsportfolio.backend.util;

import org.springframework.http.ResponseEntity;

public interface FormulaOneExternalAPI {
    public ResponseEntity<String> getRaceCalendar();
}
