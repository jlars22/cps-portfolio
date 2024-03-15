package org.cpsportfolio.backend.service.external.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RaceTable {
    @JsonProperty("Races")
    private List<RacesItem> races;

    private String season;
}
