package org.cpsportfolio.backend.external.generated.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    private String country;
    private String locality;
    private String lat;

    @JsonProperty("long")
    private String lon;
}
