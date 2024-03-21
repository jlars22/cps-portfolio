package org.cpsportfolio.backend.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.external.FormulaOneAPI;
import org.cpsportfolio.backend.external.generated.driverstanding.DriverStandingsItem;
import org.cpsportfolio.backend.external.generated.driverstanding.MRData;
import org.cpsportfolio.backend.external.generated.driverstanding.MrDataWrapperDriverStandings;
import org.cpsportfolio.backend.repository.DriverStandingsRepository;
import org.cpsportfolio.backend.service.dto.driverstandings.DriverInfo;
import org.cpsportfolio.backend.service.dto.driverstandings.DriverStandingsDto;
import org.cpsportfolio.backend.util.CountryCodes;
import org.cpsportfolio.backend.util.JsonUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverStandingsService {

    private final FormulaOneAPI formulaOneExternal;
    private final DriverStandingsRepository driverStandingsRepository;

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
                    CountryCodes.getCode(driver.getDriver().getNationality()),
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
        return JsonUtil.parseJson(externalResponse, MrDataWrapperDriverStandings.class).getMrData();
    }

    private MRData getCurrentDriverStandingsMRData() {
        return getMRData(formulaOneExternal.getCurrentDriverStandings());
    }

    private MRData getDriverStandingsByRoundMRData(int round) {
        return getMRData(formulaOneExternal.getCurrentDriverStandingsByRound(round));
    }
}
