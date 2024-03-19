import { Card } from "@material-tailwind/react";
import DriverChampionshipChart from "./DriverChampionshipChart";

const championshipData = [
  { name: "Max Verstappen", points: 26 },
  { name: "Sergio Pérez", points: 18 },
  { name: "Charles Leclerc", points: 12 },
  { name: "Lewis Hamilton", points: 8 },
  { name: "Lando Norris", points: 6 },
];

const Overview = () => {
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
