package org.cpsportfolio.backend.service.external;

import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.util.HttpClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErgastAPI implements FormulaOneExternalAPI {

    private final HttpClient httpClient;
    private final String baseUrl = "https://ergast.com/api/f1";

    @Override
    public ResponseEntity<String> getCurrentRaceCalendar() {
        return httpClient.doGetRequest(baseUrl + "/current.json");
    }
}
