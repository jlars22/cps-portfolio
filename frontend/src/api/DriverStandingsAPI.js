import axiosInstance from "./config/axiosConfig";

const DriverStandingsAPI = {
  getCurrentDriverStandings: async () => {
    const response = await axiosInstance.get("/api/driver-standings");
    return response.data;
  },

  getCurrentDriverStandingsSimple: async () => {
    const response = await axiosInstance.get("/api/driver-standings/simple");
    return response.data;
  },
};

export default DriverStandingsAPI;
