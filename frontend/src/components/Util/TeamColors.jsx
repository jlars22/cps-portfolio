const teamColors = {
  "Red Bull": "#3671C4",
  "Max Verstappen": "#3671C4",
  "Sergio Pérez": "#3671C4",

  Ferrari: "#E9012D",
  "Charles Leclerc": "#E9012D",
  "Carlos Sainz": "#E9012D",

  Mercedes: "#29F3D6",
  "Lewis Hamilton": "#29F3D6",
  "George Russell": "#29F3D6",

  McLaren: "#FF8002",
  "Lando Norris": "#FF8002",
  "Oscar Piastri": "#FF8002",

  "Aston Martin": "#239974",
  "Fernando Alonso": "#239974",
  "Lance Stroll": "#239974",

  "Haas F1 Team": "#B6BABF",
  "Kevin Magnussen": "#B6BABF",
  "Nico Hülkenberg": "#B6BABF",

  Williams: "#65C3FD",
  "Alexander Albon": "#65C3FD",
  "Logan Sargeant": "#65C3FD",

  Sauber: "#52E252",
  "Valtteri Bottas": "#52E252",
  "Guanyu Zhou": "#52E252",

  "RB F1 Team": "#6790FE",
  "Daniel Ricciardo": "#6790FE",
  "Yuki Tsunoda": "#6790FE",

  "Alpine F1 Team": "#FC87BA",
  "Esteban Ocon": "#FC87BA",
  "Pierre Gasly": "#FC87BA",
};

const getTeamColor = (teamNameOrDriverName) =>
  teamColors[teamNameOrDriverName] || "#000000";

export { getTeamColor };
