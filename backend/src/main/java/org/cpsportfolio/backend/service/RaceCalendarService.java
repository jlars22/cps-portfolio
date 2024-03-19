package org.cpsportfolio.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cpsportfolio.backend.external.generated.calendar.MRData;
import org.cpsportfolio.backend.external.generated.calendar.MRDataWrapperCalendar;
import org.cpsportfolio.backend.external.generated.calendar.RacesItem;
import org.cpsportfolio.backend.service.dto.racecalendar.RaceCalendarDto;
import org.cpsportfolio.backend.service.dto.racecalendar.Session;
import org.cpsportfolio.backend.util.CountryCodes;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceCalendarService {

    private final FormulaOneAPI formulaOneExternal;
    private final CountryCodes countryCodes;
    private final ObjectMapper objectMapper;

    public List<RaceCalendarDto> getCurrentRaceCalendar() {
        MRData data = getCurrentRaceCalendarMRData();
        return convertExternalResponseToRaceCalendarDtos(data);
    }

    public RaceCalendarDto getNextRace() {
        MRData data = getCurrentRaceCalendarMRData();
        return findNextRaceItem(data);
    }

    public int getRaceCount() {
        MRData data = getCurrentRaceCalendarMRData();
        return data.getRaceTable().getRaces().size();
    }

    private MRData getCurrentRaceCalendarMRData() {
        String externalResponse = formulaOneExternal.getCurrentRaceCalendar();
        try {
            return objectMapper
                .readValue(externalResponse, MRDataWrapperCalendar.class)
                .getMrData();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private RaceCalendarDto findNextRaceItem(MRData data) {
        List<RaceCalendarDto> raceCalendarDto = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        for (RacesItem raceItem : data.getRaceTable().getRaces()) {
            LocalDate raceDate = LocalDate.parse(raceItem.getDate());

            if (raceDate.isAfter(currentDate) || raceDate.isEqual(currentDate)) {
                addRaceItemToRaceCalendarDtos(raceItem, raceCalendarDto);
                highlightNextSession(raceCalendarDto);
                return raceCalendarDto.get(0);
            }
        }

        return null;
    }

    private List<RaceCalendarDto> convertExternalResponseToRaceCalendarDtos(MRData data) {
        List<RaceCalendarDto> raceCalendarDtos = new ArrayList<>();

        for (RacesItem raceItem : data.getRaceTable().getRaces()) {
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
        String date = formatDateToDayOfWeekName(raceItem.getDate());
        String alpha2CountryCode = countryCodes.getCode(
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

        if (isSprintWeekend(raceItem)) {
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
        } else {
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

    private boolean isSprintWeekend(RacesItem raceItem) {
        return raceItem.getSprint() != null;
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

    private String formatDateToDayOfWeekName(String formattedDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(formattedDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return date.format(outputFormatter);
    }
}
