package org.cpsportfolio.backend.external.generated.driverstanding;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class StandingsListsItem {

    private String round;

    @JsonProperty("DriverStandings")
    private List<DriverStandingsItem> driverStandings;

    private String season;
}
