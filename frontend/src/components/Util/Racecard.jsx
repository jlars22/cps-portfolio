import Flag from "react-flagkit";

const Racecard = ({ race }) => {
  const nextSession = [...race.sessions]
    .sort((a, b) => a.day - b.day)
    .find((session) => {
      return session.day > new Date();
    });

  const nextSessionIndex = race.sessions.indexOf(nextSession);

  return (
    <div className=" h-72 w-96 max-w-lg rounded-lg bg-gray-700 p-4 shadow-md">
      <div className="mt-2 flex  gap-2">
        <Flag country={race.country} size={100} />
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
          {race.sessions.map((session, index) => (
            <tr
              key={index}
              className={index === nextSessionIndex ? "bg-red-600" : ""}
            >
              <td className="text-left text-gray-300 ">{session.name}</td>
              <td className="text-right text-gray-300 ">
                {session.day.toLocaleDateString(undefined, {
                  weekday: "short",
                })}
              </td>
              <td className="text-right text-gray-300 ">
                {session.day.toLocaleTimeString(undefined, {
                  hour: "2-digit",
                  minute: "2-digit",
                })}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Racecard;
