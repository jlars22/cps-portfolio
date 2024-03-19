package org.cpsportfolio.backend.service;

public interface FormulaOneAPI {
    String getCurrentRaceCalendar();
    String getCurrentDriverStandings();
    String getCurrentDriverStandingsByRound(int round);
}
