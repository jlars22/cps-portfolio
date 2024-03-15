const races = [
  {
    name: "Bahrain Grand Prix",
    circuit: "Bahrain International Circuit, Sakhir",
    date: "March 2nd",
    country: "BH",
    sessions: [
      { name: "Practice 1", day: new Date("2024-03-01T12:30:00") },
      { name: "Practice 2", day: new Date("2024-03-01T16:00:00") },
      { name: "Practice 3", day: new Date("2024-03-02T12:30:00") },
      { name: "Qualifying", day: new Date("2024-03-02T16:00:00") },
      { name: "Race", day: new Date("2024-03-02T15:00:00") },
    ],
  },
  {
    name: "Saudi Arabian Grand Prix",
    circuit: "Jeddah Corniche Circuit, Jeddah",
    date: "March 9th",
    country: "SA",
    sessions: [
      { name: "Practice 1", day: new Date("2024-03-08T12:30:00") },
      { name: "Practice 2", day: new Date("2024-03-08T16:00:00") },
      { name: "Practice 3", day: new Date("2024-03-09T12:30:00") },
      { name: "Qualifying", day: new Date("2024-03-09T16:00:00") },
      { name: "Race", day: new Date("2024-03-09T15:00:00") },
    ],
  },
  {
    name: "Australian Grand Prix",
    circuit: "Albert Park Circuit, Melbourne",
    date: "March 22nd - 24th",
    country: "AU",
    sessions: [
      { name: "Practice 1", day: new Date("2024-03-22T12:30:00") },
      { name: "Practice 2", day: new Date("2024-03-22T16:00:00") },
      { name: "Practice 3", day: new Date("2024-03-23T12:30:00") },
      { name: "Qualifying", day: new Date("2024-03-23T16:00:00") },
      { name: "Race", day: new Date("2024-03-24T15:00:00") },
    ],
  },
];

export default races;
