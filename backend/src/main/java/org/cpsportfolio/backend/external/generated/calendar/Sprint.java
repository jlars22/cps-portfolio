package org.cpsportfolio.backend.external.generated.calendar;

import lombok.Data;

@Data
public class Sprint {

    private String date;
    private String time;

    @Override
    public String toString() {
        return "Sprint Race";
    }
}
