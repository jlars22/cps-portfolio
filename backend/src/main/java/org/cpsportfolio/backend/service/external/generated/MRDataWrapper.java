package org.cpsportfolio.backend.service.external.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MRDataWrapper {
    @JsonProperty("MRData")
    private MRData mrData;
}
