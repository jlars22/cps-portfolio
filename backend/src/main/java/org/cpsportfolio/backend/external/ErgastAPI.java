package org.cpsportfolio.backend.external;

import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.util.HttpClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErgastAPI implements FormulaOneAPI {

    private final HttpClient httpClient;
    private final String baseUrl = "https://ergast.com/api/f1";

    @Override
    public String getCurrentRaceCalendar() {
        return httpClient.doGetRequest(baseUrl + "/current.json").getBody();
    }

    @Override
    public String getCurrentDriverStandings() {
        return httpClient.doGetRequest(baseUrl + "/2024/driverStandings.json").getBody();
    }

    @Override
    public String getCurrentDriverStandingsByRound(int round) {
        return httpClient
            .doGetRequest(baseUrl + "/2024/" + round + "/driverStandings.json")
            .getBody();
    }
}
