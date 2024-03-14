import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import TopBar from "components/TopBar/TopBar";
import Logo from "components/Util/Logo";
import DriverStandings from "components/DriverStandings/DriverStandings";
import Calendar from "components/Calendar/Calendar";
import Home from "components/Home/Home";

export default function App() {
  const links = [
    {
      label: "Home",
      path: "/",
      component: <Home />,
      logo: <Logo />,
    },
    {
      label: "Driver Standings",
      path: "/driver-standings",
      component: <DriverStandings />,
      logo: <Logo type="driverstandings" />,
    },
    {
      label: "Calendar",
      path: "/calendar",
      component: <Calendar />,
      logo: <Logo type="calendar" />,
    },
  ];

  return (
    <Router>
      <TopBar links={links} />
      <Routes>
        {links.map((tab, index) => (
          <Route key={index} path={tab.path} element={tab.component} />
        ))}
      </Routes>
    </Router>
  );
}
