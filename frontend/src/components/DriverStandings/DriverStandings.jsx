import { Card } from "@material-tailwind/react";
import DriverStandingsAPI from "api/DriverStandingsAPI";
import CarAvatar from "components/Util/CarAvatar";
import DriverAvatar from "components/Util/DriverAvatar";
import { useEffect, useState } from "react";
import ReactCountryFlag from "react-country-flag";

const DriverStandings = () => {
  const [driverStandingsData, setDriverStandingsData] = useState([]);

  useEffect(() => {
    const fetchDriverStandingsData = async () => {
      try {
        const data = await DriverStandingsAPI.getCurrentDriverStandings();
        setDriverStandingsData(data);
      } catch (error) {
        console.error("Error fetching driver standings data:", error);
      }
    };

    fetchDriverStandingsData();
  }, []);

  const TABLE_HEAD = [
    "Position",
    "Driver",
    "Constructor",
    "Car",
    "Wins",
    "Points",
  ];

  return (
    <Card className="mb-4 ml-4 mr-4 mt-4 rounded-2xl bg-gray-800 p-4 shadow-md">
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
          {driverStandingsData.driverInfo?.map(
            (
              { position, name, nationality, car, wins, points, code },
              index,
            ) => {
              const classes = "p-4 text-gray-300 border-b border-gray-600";

              return (
                <tr key={index}>
                  <td className={classes}>{position}</td>
                  <td className={classes}>
                    <div className="flex items-center">
                      <div className="flex h-16 w-16 items-center justify-center overflow-hidden rounded-full bg-gray-600 shadow-md">
                        <DriverAvatar driverCode={code} size="70px" />
                      </div>
                      <div className="ml-2 flex flex-col">
                        <ReactCountryFlag
                          countryCode={nationality}
                          style={{
                            width: "30px",
                            height: "21px",
                          }}
                          svg
                        />
                        <span>{name}</span>
                      </div>
                    </div>
                  </td>
                  <td className={classes}>{car}</td>
                  <td className={classes}>
                    <CarAvatar teamName={car} size="200px" />
                  </td>
                  <td className={classes}>{wins}</td>
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
