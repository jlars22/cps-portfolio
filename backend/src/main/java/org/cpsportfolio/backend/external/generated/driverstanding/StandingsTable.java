package org.cpsportfolio.backend.external.generated.driverstanding;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class StandingsTable {

    @JsonProperty("StandingsLists")
    private List<StandingsListsItem> standingsLists;

    private String season;
}
