package org.cpsportfolio.backend.external.generated.calendar;

import lombok.Data;

@Data
public class ThirdPractice {

    private String date;
    private String time;

    @Override
    public String toString() {
        return "Practice 3";
    }
}
