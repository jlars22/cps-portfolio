package org.cpsportfolio.backend.external.generated.driverstanding;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class DriverStandingsItem {

    private String wins;
    private String positionText;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructors")
    private List<ConstructorsItem> constructors;

    private String position;
    private String points;
}
