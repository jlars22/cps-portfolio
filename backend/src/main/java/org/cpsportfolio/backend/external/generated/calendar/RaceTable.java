package org.cpsportfolio.backend.external.generated.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class RaceTable {

    @JsonProperty("Races")
    private List<RacesItem> races;

    private String season;
}
