package org.cpsportfolio.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.service.dto.RaceCalendarDto;
import org.cpsportfolio.backend.service.external.FormulaOneExternalAPI;
import org.cpsportfolio.backend.util.CountryCodes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceCalendarService {

    private final FormulaOneExternalAPI formulaOneExternal;
    private final CountryCodes countryCodes = new CountryCodes();

    public List<RaceCalendarDto> getCurrentRaceCalendar() throws Exception {
        String jsonResponse = formulaOneExternal.getCurrentRaceCalendar().getBody();
        return mapJsonToRaceCalendarDto(jsonResponse);
    }

    private List<RaceCalendarDto> mapJsonToRaceCalendarDto(String jsonResponse) throws Exception {
        JsonNode racesNode = getRacesNode(jsonResponse);

        List<RaceCalendarDto> raceCalendarDtos = new ArrayList<>();

        for (JsonNode raceNode : racesNode) {
            String name = raceNode.path("raceName").asText();
            String circuit = raceNode.path("Circuit").path("circuitName").asText();
            String formattedDate = formatDateToDayOfWeek(raceNode.path("date").asText());
            String alpha2CountryCode = convertCountryNameToAlpha2Code(
                    raceNode.path("Circuit").path("Location").path("country").asText());

            raceCalendarDtos.add(new RaceCalendarDto(name, circuit, formattedDate, alpha2CountryCode, null));
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

    private JsonNode getRacesNode(String jsonResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);
        return rootNode.path("MRData").path("RaceTable").path("Races");
    }
}
