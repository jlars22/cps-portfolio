import { Card } from "@material-tailwind/react";
import CalendarAPI from "api/CalendarAPI";
import Racecard from "components/Util/Racecard";
import { useEffect, useState } from "react";

const Home = () => {
  const [upcommingRace, setUpCommingRace] = useState([]);

  useEffect(() => {
    const fetchUpcommingRace = async () => {
      try {
        console.log("Fetching up");
        const data = await CalendarAPI.getNextRace();
        console.log(data);
        setUpCommingRace(data);
      } catch (error) {
        console.error("Error fetching calendar data:", error);
      }
    };

    fetchUpcommingRace();
  }, []);

  return (
    <Card className="ml-4 mr-4 mt-4 rounded-2xl bg-gray-800 p-4 shadow-md">
      <div className="mb-4 flex flex-col items-center">
        <h1 className="mb-2 text-4xl font-bold text-gray-300">
          Next on the calendar
        </h1>
        {upcommingRace && <Racecard race={upcommingRace} />}
      </div>
    </Card>
  );
};

export default Home;
