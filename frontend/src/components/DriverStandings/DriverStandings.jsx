import { Card, Typography } from "@material-tailwind/react";
import DriverAvatar from "components/Util/DriverAvatar";

const DriverStandings = () => {
  const drivers = [
    {
      position: "1",
      name: "Max Verstappen",
      nationality: "Dutch",
      car: "Red Bull Racing",
      points: "51",
      code: "VER",
    },
    {
      position: "2",
      name: "Sergio Perez",
      nationality: "Mexican",
      car: "Red Bull Racing",
      points: "36",
      code: "PER",
    },
  ];

  const TABLE_HEAD = ["Position", "Driver", "Nationality", "Car", "Points"];

  return (
    <Card className=" ml-4 mr-4 mt-4 rounded-2xl bg-gray-800 p-4 shadow-md">
      <table className="w-full min-w-max table-auto text-left">
        <thead>
          <tr>
            {TABLE_HEAD.map((head) => (
              <th key={head} className=" bg-gray-600 p-4 text-gray-300 ">
                {head}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {drivers.map(
            ({ position, name, nationality, car, points, code }, index) => {
              const classes = "p-4 text-gray-300 border-b border-gray-600";

              return (
                <tr>
                  <td className={classes}>{position}</td>
                  <td className={classes}>{name}</td>
                  <td className={classes}>{nationality}</td>
                  <td className={classes}>{car}</td>
                  <td className={classes}>{points}</td>
                </tr>
              );
            },
          )}
        </tbody>
      </table>
    </Card>
  );
};

export default DriverStandings;
