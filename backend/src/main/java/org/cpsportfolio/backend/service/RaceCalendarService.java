package org.cpsportfolio.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.external.generated.MRData;
import org.cpsportfolio.backend.external.generated.MRDataWrapper;
import org.cpsportfolio.backend.external.generated.RacesItem;
import org.cpsportfolio.backend.service.dto.RaceCalendarDto;
import org.cpsportfolio.backend.service.dto.Session;
import org.cpsportfolio.backend.util.CountryCodes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceCalendarService {

    private final FormulaOneAPI formulaOneExternal;
    private final CountryCodes countryCodes = new CountryCodes();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<RaceCalendarDto> getCurrentRaceCalendar() {
        try {
            String externalResponse = formulaOneExternal.getCurrentRaceCalendar().getBody();
            MRDataWrapper data = objectMapper.readValue(externalResponse, MRDataWrapper.class);
            return mapExternalResponseToRaceCalendarDto(data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve external data", e);
        }
    }

    private List<RaceCalendarDto> mapExternalResponseToRaceCalendarDto(MRDataWrapper data) {
        MRData mrData = data.getMrData();
        List<RaceCalendarDto> raceCalendarDtos = new ArrayList<>();

        for (RacesItem raceItem : mrData.getRaceTable().getRaces()) {
            String name = raceItem.getRaceName();
            String circuit = raceItem.getCircuit().getCircuitName();
            String date = formatDateToDayOfWeek(raceItem.getDate());
            String alpha2CountryCode = convertCountryNameToAlpha2Code(
                raceItem.getCircuit().getLocation().getCountry()
            );

            List<Session> sessions = createSessions(raceItem);

            raceCalendarDtos.add(
                new RaceCalendarDto(name, circuit, date, alpha2CountryCode, sessions)
            );
        }

        return raceCalendarDtos;
    }

    private List<Session> createSessions(RacesItem raceItem) {
        List<Session> sessions = new ArrayList<>();

        sessions.add(
            createSession(
                "Practice 1",
                raceItem.getFirstPractice().getDate(),
                raceItem.getFirstPractice().getTime()
            )
        );

        if (raceItem.getThirdPractice() != null) {
            sessions.add(
                createSession(
                    "Practice 2",
                    raceItem.getSecondPractice().getDate(),
                    raceItem.getSecondPractice().getTime()
                )
            );
            sessions.add(
                createSession(
                    "Practice 3",
                    raceItem.getThirdPractice().getDate(),
                    raceItem.getThirdPractice().getTime()
                )
            );
        } else if (raceItem.getSprint() != null) {
            sessions.add(
                createSession(
                    "Sprint Qualifying",
                    raceItem.getSecondPractice().getDate(),
                    raceItem.getSecondPractice().getTime()
                )
            );
            sessions.add(
                createSession(
                    "Sprint Race",
                    raceItem.getSprint().getDate(),
                    raceItem.getSprint().getTime()
                )
            );
        }

        sessions.add(
            createSession(
                "Qualifying",
                raceItem.getQualifying().getDate(),
                raceItem.getQualifying().getTime()
            )
        );
        sessions.add(createSession("Race", raceItem.getDate(), raceItem.getTime()));

        return sessions;
    }

    private Session createSession(String name, String date, String time) {
        String dateTime = date + "T" + time;
        return new Session(name, dateTime);
    }

    private String formatDateToDayOfWeek(String formattedDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(formattedDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return date.format(outputFormatter);
    }

    public String convertCountryNameToAlpha2Code(String countryName) {
        return countryCodes.getCode(countryName);
    }
}
