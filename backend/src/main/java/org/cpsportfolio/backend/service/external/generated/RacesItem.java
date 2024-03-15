package org.cpsportfolio.backend.service.external.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RacesItem {
    private String date;
    private String round;

    @JsonProperty("FirstPractice")
    private FirstPractice firstPractice;

    @JsonProperty("ThirdPractice")
    private ThirdPractice thirdPractice;

    private String season;
    private String raceName;

    @JsonProperty("Circuit")
    private Circuit circuit;

    private String time;

    @JsonProperty("SecondPractice")
    private SecondPractice secondPractice;

    @JsonProperty("Qualifying")
    private Qualifying qualifying;

    @JsonProperty("Sprint")
    private Sprint sprint;

    private String url;
}
