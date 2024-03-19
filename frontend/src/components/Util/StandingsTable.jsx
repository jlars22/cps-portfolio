const StandingsTable = ({ headers, data, renderRow }) => (
  <table className="w-full min-w-max table-auto text-left">
    <thead>
      <tr>
        {headers.map((head) => (
          <th key={head} className="bg-gray-600 p-4 text-gray-300">
            {head}
          </th>
        ))}
      </tr>
    </thead>
    <tbody>{data.map((item, index) => renderRow(item, index))}</tbody>
  </table>
);

export default StandingsTable;
