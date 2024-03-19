package org.cpsportfolio.backend.service.dto.driverstandings;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverStandingsDto {

    private int round;
    private List<DriverInfo> driverInfo;
}
