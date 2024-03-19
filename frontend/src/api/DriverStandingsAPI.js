import axiosInstance from "./config/axiosConfig";

const DriverStandingsAPI = {
  getCurrentDriverStandings: async () => {
    const response = await axiosInstance.get("/api/driver-standings");
    return response.data;
  },
};

export default DriverStandingsAPI;
