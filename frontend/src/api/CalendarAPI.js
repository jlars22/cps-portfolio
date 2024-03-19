import axiosInstance from "./config/axiosConfig";

const CalendarAPI = {
  getCurrentCalendar: async () => {
    const response = await axiosInstance.get("/api/calendar");
    return response.data;
  },

  getNextRace: async () => {
    const response = await axiosInstance.get("/api/calendar/next");
    return response.data;
  },

  getRaceCount: async () => {
    const response = await axiosInstance.get("/api/calendar/count");
    return response.data;
  },
};

export default CalendarAPI;
