import { Card } from "@material-tailwind/react";
import Racecard from "components/Util/Racecard";

const Home = () => {
  return (
    <Card className="ml-4 mr-4 mt-4 rounded-2xl bg-gray-800 p-4 shadow-md">
      <div className="mb-4 flex flex-col items-center">
        <h1 className="mb-2 text-4xl font-bold text-gray-300">
          Next on the calendar
        </h1>
        <Racecard />
      </div>
    </Card>
  );
};

export default Home;
