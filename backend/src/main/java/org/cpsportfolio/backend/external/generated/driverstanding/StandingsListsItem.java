package org.cpsportfolio.backend.external.generated.driverstanding;

import java.util.List;
import lombok.Data;

@Data
public class StandingsListsItem{
	private String round;
	private List<DriverStandingsItem> driverStandings;
	private String season;
}