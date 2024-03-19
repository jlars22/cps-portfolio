package org.cpsportfolio.backend.external.generated.driverstanding;

import java.util.List;
import lombok.Data;

@Data
public class DriverStandingsItem{
	private String wins;
	private String positionText;
	private Driver driver;
	private List<ConstructorsItem> constructors;
	private String position;
	private String points;
}