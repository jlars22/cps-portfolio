package org.cpsportfolio.backend.service.dto.driverstandings;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverInfo {

    private String position;
    private String name;
    private String nationality;
    private String car;
    private String points;
    private String code;
    private String wins;
}
