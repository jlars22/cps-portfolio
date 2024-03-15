import { useState } from "react";
import { Card, List, ListItem, ListItemPrefix } from "@material-tailwind/react";
import Racecard from "components/Util/Racecard";
import Flag from "react-flagkit";
import races from "../Util/races";

const Calendar = () => {
  const upcomingRaceIndex = races.findIndex(
    (race) => race.sessions[0].day > new Date(),
  );

  const [selected, setSelected] = useState(upcomingRaceIndex);

  return (
    <Card className=" ml-4 mr-4 mt-4 h-auto rounded-2xl bg-gray-800 p-4 shadow-md">
      <div className="flex items-center">
        <List className="mr-4 rounded-lg bg-gray-700 text-gray-300 shadow-md">
          {races.map((race, index) => (
            <ListItem
              key={index}
              onClick={() => setSelected(index)}
              selected={selected === index}
            >
              <ListItemPrefix>
                <Flag country={race.country} size={24} />
              </ListItemPrefix>
              {race.name}
            </ListItem>
          ))}
        </List>
        {races[selected] && (
          <Card>
            <Racecard race={races[selected]} />
          </Card>
        )}
      </div>
    </Card>
  );
};

export default Calendar;
