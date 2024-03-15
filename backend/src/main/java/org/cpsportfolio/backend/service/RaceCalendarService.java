package org.cpsportfolio.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.dto.RaceCalendarDto;
import org.cpsportfolio.backend.service.dto.Session;
import org.cpsportfolio.backend.service.external.FormulaOneExternalAPI;
import org.cpsportfolio.backend.service.external.generated.MRData;
import org.cpsportfolio.backend.service.external.generated.MRDataWrapper;
import org.cpsportfolio.backend.service.external.generated.RacesItem;
import org.cpsportfolio.backend.util.CountryCodes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceCalendarService {

    private final FormulaOneExternalAPI formulaOneExternal;
    private final CountryCodes countryCodes = new CountryCodes();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<RaceCalendarDto> getCurrentRaceCalendar() throws Exception {
        String jsonResponse = formulaOneExternal.getCurrentRaceCalendar().getBody();
        MRDataWrapper data = objectMapper.readValue(jsonResponse, MRDataWrapper.class);

        return mapJsonToRaceCalendarDto(data);
    }

    private List<RaceCalendarDto> mapJsonToRaceCalendarDto(MRDataWrapper data) {
        MRData mrData = data.getMrData();
        List<RaceCalendarDto> raceCalendarDtos = new ArrayList<>();

        for (RacesItem raceItem : mrData.getRaceTable().getRaces()) {
            String name = raceItem.getRaceName();
            String circuit = raceItem.getCircuit().getCircuitName();
            String formattedDate = formatDateToDayOfWeek(raceItem.getDate());
            String alpha2CountryCode = convertCountryNameToAlpha2Code(
                    raceItem.getCircuit().getLocation().getCountry());

            Session practice1 = new Session(
                    raceItem.getFirstPractice().toString(),
                    raceItem.getFirstPractice().getDate() + "T"
                            + raceItem.getFirstPractice().getTime());

            Session practice2 = new Session(
                    raceItem.getSecondPractice().toString(),
                    raceItem.getSecondPractice().getDate() + "T"
                            + raceItem.getSecondPractice().getTime());

            Session practice3orSprint = null;
            if (raceItem.getThirdPractice() != null) {
                practice3orSprint = new Session(
                        raceItem.getThirdPractice().toString(),
                        raceItem.getThirdPractice().getDate() + "T"
                                + raceItem.getThirdPractice().getTime());
            } else if (raceItem.getSprint() != null) {
                practice3orSprint = new Session(
                        raceItem.getSprint().toString(),
                        raceItem.getSprint().getDate() + "T"
                                + raceItem.getSprint().getTime());
            }

            Session qualifying = new Session(
                    raceItem.getQualifying().toString(),
                    raceItem.getQualifying().getDate() + "T"
                            + raceItem.getQualifying().getTime());

            Session race = new Session("Race", raceItem.getDate() + "T" + raceItem.getTime());

            raceCalendarDtos.add(new RaceCalendarDto(
                    name,
                    circuit,
                    formattedDate,
                    alpha2CountryCode,
                    List.of(practice1, practice2, practice3orSprint, qualifying, race)));
        }

        return raceCalendarDtos;
    }

    private String formatDateToDayOfWeek(String formattedDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(formattedDate, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy");
        return date.format(outputFormatter);
    }

    public String convertCountryNameToAlpha2Code(String countryName) {
        return countryCodes.getCode(countryName);
    }
}
