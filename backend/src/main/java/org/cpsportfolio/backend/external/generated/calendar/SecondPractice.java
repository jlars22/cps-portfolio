package org.cpsportfolio.backend.external.generated;

import lombok.Data;

@Data
public class SecondPractice {

    private String date;
    private String time;

    @Override
    public String toString() {
        return "Practice 2";
    }
}
