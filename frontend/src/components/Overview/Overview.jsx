import { Card } from "@material-tailwind/react";
import DriverChampionshipChart from "./DriverChampionshipChart";
import { useEffect, useState } from "react";
import DriverStandingsAPI from "api/DriverStandingsAPI";

const Overview = () => {
  const [championshipData, setChampionshipData] = useState([]);

  useEffect(() => {
    const fetchChampionshipData = async () => {
      const data = await DriverStandingsAPI.getCurrentDriverStandingsSimple();
      setChampionshipData(data);
    };

    fetchChampionshipData();
  }, []);

  return (
    <Card className="mb-4 ml-4 mr-4 mt-4 flex items-center rounded-2xl bg-gray-800 p-4 shadow-md">
      <h1 className="mb-2 text-4xl font-bold text-gray-300">
        Driver championship points overview
      </h1>

      <DriverChampionshipChart data={championshipData} />
    </Card>
  );
};

export default Overview;
