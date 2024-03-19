import { useState, useEffect } from "react";
import { Card, List, ListItem, ListItemPrefix } from "@material-tailwind/react";
import Racecard from "components/Util/Racecard";

import CalendarAPI from "api/CalendarAPI";
import ReactCountryFlag from "react-country-flag";

const Calendar = () => {
  const [calendarData, setCalendarData] = useState([]);
  const [selected, setSelected] = useState(null);

  useEffect(() => {
    const fetchCalendarData = async () => {
      try {
        const data = await CalendarAPI.getCurrentCalendar();
        setCalendarData(data);
      } catch (error) {
        console.error("Error fetching calendar data:", error);
      }
    };

    fetchCalendarData();
  }, []);

  useEffect(() => {
    const upcomingRaceIndex = calendarData.findIndex((race) =>
      race.sessions.some((session) => session.nextUp),
    );
    setSelected(upcomingRaceIndex);
  }, [calendarData]);

  return (
    <Card className=" ml-4 mr-4 mt-4 rounded-2xl bg-gray-800 p-4 shadow-md">
      <div className="flex place-items-start">
        <List className="mr-4 rounded-lg bg-gray-700 text-gray-300 shadow-md">
          {calendarData.map((race, index) => (
            <ListItem
              key={index}
              onClick={() => setSelected(index)}
              selected={selected === index}
            >
              <ListItemPrefix>
                <ReactCountryFlag
                  countryCode={race.alpha2CountryCode}
                  style={{
                    width: "30px",
                    height: "21px",
                  }}
                  svg
                />
              </ListItemPrefix>
              {race.name}
            </ListItem>
          ))}
        </List>
        {calendarData[selected] && (
          <Card>
            <Racecard race={calendarData[selected]} />
          </Card>
        )}
      </div>
    </Card>
  );
};

export default Calendar;
