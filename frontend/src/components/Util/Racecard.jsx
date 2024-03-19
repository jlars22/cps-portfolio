import ReactCountryFlag from "react-country-flag";

const Racecard = ({ race }) => {
  const races = Array.isArray(race) ? race : [race];

  return races.map((race, raceIndex) => {
    return (
      <div
        key={raceIndex}
        className=" h-72 w-screen max-w-lg rounded-lg bg-gray-700 p-4 shadow-md"
      >
        <div className="mt-2 flex  gap-2">
          <ReactCountryFlag
            countryCode={race.alpha2CountryCode}
            style={{
              width: "110px",
              height: "80px",
            }}
            svg
          />
          <div className="flex flex-col">
            <p className="text-lg font-bold text-gray-300">{race.name}</p>
            <p className="text-sm text-gray-300 ">{race.circuit}</p>
            <p className="text-sm text-gray-300 ">{race.date}</p>
          </div>
        </div>

        <table className="mt-4 w-full ">
          <thead>
            <tr>
              <th className="border-b border-gray-400 pb-1 text-left text-gray-300">
                Session
              </th>
              <th className="border-b border-gray-400 pb-1 text-right text-gray-300 ">
                Day
              </th>
              <th className="border-b border-gray-400 pb-1 text-right text-gray-300 ">
                Time
              </th>
            </tr>
          </thead>
          <tbody>
            {race.sessions.map((session, index) => {
              const sessionDate = new Date(session.date);
              return (
                <tr key={index} className={session.nextUp ? "bg-red-600" : ""}>
                  <td className="text-left text-gray-300 ">{session.name}</td>
                  <td className="text-right text-gray-300 ">
                    {sessionDate.toLocaleDateString(undefined, {
                      weekday: "short",
                    })}
                  </td>
                  <td className="text-right text-gray-300 ">
                    {sessionDate.toLocaleTimeString(undefined, {
                      hour: "2-digit",
                      minute: "2-digit",
                    })}
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    );
  });
};

export default Racecard;
