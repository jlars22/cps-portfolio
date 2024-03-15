package org.cpsportfolio.backend.service.external.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Circuit {
    private String circuitId;
    private String url;
    private String circuitName;

    @JsonProperty("Location")
    private Location location;
}
