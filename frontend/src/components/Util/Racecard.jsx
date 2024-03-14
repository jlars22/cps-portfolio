import Flag from "react-flagkit";

const Racecard = () => {
  const sessions = [
    { name: "Practice 1", day: "Fri", time: "12:00" },
    { name: "Practice 2", day: "Fri", time: "16:00" },
    { name: "Practice 3", day: "Sat", time: "13:00" },
    { name: "Qualifying", day: "Sat", time: "16:00" },
    { name: "Race", day: "Sun", time: "16:00" },
  ];

  const nextSessionIndex = 2; // TODO: Calculate next session index

  return (
    <div className="w-full max-w-lg rounded-lg bg-gray-700 p-4 shadow-md">
      <div className="flex items-center justify-center gap-2">
        <Flag country="AU" size={100} />
        <div className="flex flex-col">
          <p className="text-lg font-bold text-gray-300">Australia</p>
          <p className="text-sm text-gray-300 ">Melbourne Grand Prix Circuit</p>
          <p className="text-sm text-gray-300 ">22 - 24 March 2024</p>
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
          {sessions.map((session, index) => (
            <tr
              key={index}
              className={index === nextSessionIndex ? "bg-red-600" : ""}
            >
              <td className="text-left text-gray-300 ">{session.name}</td>
              <td className="text-right text-gray-300 ">{session.day}</td>
              <td className="text-right text-gray-300 ">{session.time}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Racecard;
