import axiosInstance from "./config/axiosConfig";

const CalendarAPI = {
  getCurrentCalendar: async () => {
    const response = await axiosInstance.get("/api/calendar/current");
    return response.data;
  },

  getNextRace: async () => {
    const response = await axiosInstance.get("/api/calendar/next");
    return response.data;
  },
};

export default CalendarAPI;
