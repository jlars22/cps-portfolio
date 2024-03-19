package org.cpsportfolio.backend.external.generated.driverstanding;

import java.util.List;
import lombok.Data;

@Data
public class StandingsTable{
	private List<StandingsListsItem> standingsLists;
	private String season;
}