package org.cpsportfolio.backend.external.generated.driverstanding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MRData {

    private String xmlns;
    private String total;
    private String offset;
    private String series;
    private String limit;

    @JsonProperty("StandingsTable")
    private StandingsTable standingsTable;

    private String url;
}
