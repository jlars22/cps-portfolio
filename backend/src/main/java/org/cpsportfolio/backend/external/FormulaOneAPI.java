package org.cpsportfolio.backend.external;

public interface FormulaOneAPI {
    String getCurrentRaceCalendar();
    String getCurrentDriverStandings();
    String getCurrentDriverStandingsByRound(int round);
}
