package org.cpsportfolio.backend.external.generated.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MRData {

    @JsonProperty("RaceTable")
    private RaceTable raceTable;

    private String xmlns;
    private String total;
    private String offset;
    private String series;
    private String limit;
    private String url;
}
