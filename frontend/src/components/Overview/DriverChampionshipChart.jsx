import {
  Bar,
  BarChart,
  Cell,
  ResponsiveContainer,
  XAxis,
  YAxis,
} from "recharts";
import { getTeamColor } from "components/Util/TeamColors";

const DriverChampionshipChart = ({ data }) => {
  return (
    <ResponsiveContainer width="100%" aspect={2} className="mr-2">
      <BarChart data={data} layout="vertical" margin={{ left: 30, right: 90 }}>
        <XAxis type="number" orientation="top" stroke="#E4E5E8" />
        <YAxis
          type="category"
          dataKey="name"
          axisLine={false}
          dx={0}
          tickLine={false}
          style={{ fill: "#E4E5E8" }}
        />
        <Bar dataKey="points" barSize={{ height: 26 }}>
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={getTeamColor(entry.name)} />
          ))}
        </Bar>
      </BarChart>
    </ResponsiveContainer>
  );
};

export default DriverChampionshipChart;
