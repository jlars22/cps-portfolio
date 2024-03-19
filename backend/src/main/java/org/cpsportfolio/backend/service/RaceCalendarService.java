package org.cpsportfolio.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    private final CountryCodes countryCodes;
    private final ObjectMapper objectMapper;

    public List<RaceCalendarDto> getCurrentRaceCalendar() {
        try {
            String externalResponse = formulaOneExternal.getCurrentRaceCalendar();
            MRDataWrapper data = objectMapper.readValue(externalResponse, MRDataWrapper.class);
            return convertExternalResponseToRaceCalendarDtos(data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve external data", e);
        }
    }

    public RaceCalendarDto getNextRace() {
        try {
            String externalResponse = formulaOneExternal.getCurrentRaceCalendar();
            MRDataWrapper data = objectMapper.readValue(externalResponse, MRDataWrapper.class);
            return findNextRaceItem(data);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve external data", e);
        }
    }

    private RaceCalendarDto findNextRaceItem(MRDataWrapper data) {
        MRData mrData = data.getMrData();
        List<RaceCalendarDto> raceCalendarDto = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        for (RacesItem raceItem : mrData.getRaceTable().getRaces()) {
            LocalDate raceDate = LocalDate.parse(raceItem.getDate());

            if (raceDate.isAfter(currentDate) || raceDate.isEqual(currentDate)) {
                addRaceItemToRaceCalendarDtos(raceItem, raceCalendarDto);
                highlightNextSession(raceCalendarDto);
                return raceCalendarDto.get(0);
            }
        }

        return null;
    }

    private List<RaceCalendarDto> convertExternalResponseToRaceCalendarDtos(MRDataWrapper data) {
        MRData mrData = data.getMrData();
        List<RaceCalendarDto> raceCalendarDtos = new ArrayList<>();

        for (RacesItem raceItem : mrData.getRaceTable().getRaces()) {
            addRaceItemToRaceCalendarDtos(raceItem, raceCalendarDtos);
        }

        highlightNextSession(raceCalendarDtos);

        return raceCalendarDtos;
    }

    private void addRaceItemToRaceCalendarDtos(
        RacesItem raceItem,
        List<RaceCalendarDto> raceCalendarDtos
    ) {
        String name = raceItem.getRaceName();
        String circuit = raceItem.getCircuit().getCircuitName();
        String date = formatDateToReadableFormat(raceItem.getDate());
        String alpha2CountryCode = convertCountryNameToAlpha2Code(
            raceItem.getCircuit().getLocation().getCountry()
        );

        List<Session> sessions = createSessions(raceItem);

        raceCalendarDtos.add(new RaceCalendarDto(name, circuit, date, alpha2CountryCode, sessions));
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

    private void highlightNextSession(List<RaceCalendarDto> raceCalendarDtos) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);

        for (RaceCalendarDto raceCalendarDto : raceCalendarDtos) {
            for (Session session : raceCalendarDto.getSessions()) {
                Instant sessionInstant = Instant.parse(session.getDate());
                LocalDateTime sessionDateTime = LocalDateTime.ofInstant(
                    sessionInstant,
                    ZoneOffset.UTC
                );

                if (sessionDateTime.isAfter(now)) {
                    session.setNextUp(true);
                    return;
                }
            }
        }
    }

    private Session createSession(String name, String date, String time) {
        String dateTime = date + "T" + time;
        return new Session(name, dateTime, false);
    }

    private String formatDateToReadableFormat(String formattedDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(formattedDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return date.format(outputFormatter);
    }

    private String convertCountryNameToAlpha2Code(String countryName) {
        return countryCodes.getCode(countryName);
    }
}
