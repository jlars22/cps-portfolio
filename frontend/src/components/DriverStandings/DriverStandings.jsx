import { Card } from "@material-tailwind/react";
import CalendarAPI from "api/CalendarAPI";
import DriverStandingsAPI from "api/DriverStandingsAPI";
import CarAvatar from "components/Util/CarAvatar";
import DriverAvatar from "components/Util/DriverAvatar";
import ReactCountryFlag from "react-country-flag";
import { useEffect, useState } from "react";
import StandingsTable from "components/Util/StandingsTable";

const DriverTableRow = ({ driver }) => {
  const { position, name, nationality, car, wins, points, code } = driver;

  return (
    <tr className={getTableRowClass(position)}>
      <td className="border-b border-gray-600 p-4 text-gray-300">{position}</td>
      <td className="border-b border-gray-600 p-4 text-gray-300">
        <DriverInfo name={name} nationality={nationality} code={code} />
      </td>
      <td className="border-b border-gray-600 p-4 text-gray-300">{car}</td>
      <td className="border-b border-gray-600 p-4 text-gray-300">
        <CarAvatar teamName={car} size="200px" />
      </td>
      <td className="border-b border-gray-600 p-4 text-gray-300">{wins}</td>
      <td className="border-b border-gray-600 p-4 text-gray-300">{points}</td>
    </tr>
  );
};

const DriverInfo = ({ name, nationality, code }) => (
  <div className="flex items-center">
    <div className="flex h-16 w-16 items-center justify-center overflow-hidden rounded-full bg-gray-600 shadow-md">
      <DriverAvatar driverCode={code} size="70px" />
    </div>
    <div className="ml-2 flex flex-col">
      <ReactCountryFlag
        countryCode={nationality}
        style={{ width: "30px", height: "21px" }}
        svg
        className="shadow-md"
      />
      <span>{name}</span>
    </div>
  </div>
);

const getTableRowClass = (position) => {
  switch (position) {
    case "1":
      return "hover:bg-gold transition-colors duration-200";
    case "2":
      return "hover:bg-silver transition-colors duration-200";
    case "3":
      return "hover:bg-bronze transition-colors duration-200";
    default:
      return "transition-colors duration-200";
  }
};

const DriverStandings = () => {
  const [driverStandingsData, setDriverStandingsData] = useState([]);
  const [raceCount, setRaceCount] = useState(0);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const driverData = await DriverStandingsAPI.getCurrentDriverStandings();
      setDriverStandingsData(driverData);

      const raceData = await CalendarAPI.getRaceCount();
      setRaceCount(raceData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  return (
    <Card className="mb-4 ml-4 mr-4 mt-4 flex items-center rounded-2xl bg-gray-800 p-4 shadow-md">
      <h1 className="mb-2 text-4xl font-bold text-gray-300">
        Current Driver Standings
      </h1>
      <h4 className="mb-2 text-2xl font-bold text-gray-300">
        Round {driverStandingsData.round} of {raceCount}
      </h4>
      <StandingsTable
        headers={["Position", "Driver", "Constructor", "Car", "Wins", "Points"]}
        data={driverStandingsData.driverInfo}
        renderRow={(driver, index) => (
          <DriverTableRow key={index} driver={driver} />
        )}
      />
    </Card>
  );
};

export default DriverStandings;
