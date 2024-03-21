package org.cpsportfolio.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.external.generated.driverstanding.DriverStandingsItem;
import org.cpsportfolio.backend.external.generated.driverstanding.MRData;
import org.cpsportfolio.backend.external.generated.driverstanding.MrDataWrapperDriverStandings;
import org.cpsportfolio.backend.service.dto.driverstandings.DriverInfo;
import org.cpsportfolio.backend.service.dto.driverstandings.DriverStandingsDto;
import org.cpsportfolio.backend.util.CountryCodes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverStandingsService {

    private final FormulaOneAPI formulaOneExternal;
    private final ObjectMapper objectMapper;
    private final CountryCodes countryCodes;

    public DriverStandingsDto getCurrentDriverStandings() {
        MRData data = getCurrentDriverStandingsMRData();
        return convertExternalResponseToDriverStandingsDtos(data);
    }

    public DriverStandingsDto getDriverStandingsByRound(int round) {
        MRData data = getDriverStandingsByRoundMRData(round);
        return convertExternalResponseToDriverStandingsDtos(data);
    }

    private DriverStandingsDto convertExternalResponseToDriverStandingsDtos(MRData data) {
        int round = Integer.parseInt(
            data.getStandingsTable().getStandingsLists().get(0).getRound()
        );
        List<DriverInfo> driverInfo = new ArrayList<>();

        List<DriverStandingsItem> driverStandings = data
            .getStandingsTable()
            .getStandingsLists()
            .get(0)
            .getDriverStandings();

        for (DriverStandingsItem driver : driverStandings) {
            driverInfo.add(
                new DriverInfo(
                    driver.getPosition(),
                    driver.getDriver().getGivenName() + " " + driver.getDriver().getFamilyName(),
                    countryCodes.getCode(driver.getDriver().getNationality()),
                    driver.getConstructors().get(0).getName(),
                    driver.getPoints(),
                    driver.getDriver().getCode(),
                    driver.getWins()
                )
            );
        }

        return new DriverStandingsDto(round, driverInfo);
    }

    private MRData getMRData(String externalResponse) {
        try {
            return objectMapper
                .readValue(externalResponse, MrDataWrapperDriverStandings.class)
                .getMrData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private MRData getCurrentDriverStandingsMRData() {
        return getMRData(formulaOneExternal.getCurrentDriverStandings());
    }

    private MRData getDriverStandingsByRoundMRData(int round) {
        return getMRData(formulaOneExternal.getCurrentDriverStandingsByRound(round));
    }
}
