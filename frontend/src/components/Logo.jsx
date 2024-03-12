import React from "react";

import { IoCalendar } from "react-icons/io5";
import { GiFullMotorcycleHelmet } from "react-icons/gi";

const Logo = ({ type }) => {
  switch (type) {
    case "calendar":
      return <IoCalendar />;
    case "driverstandings":
      return <GiFullMotorcycleHelmet />;
    default:
      return null;
  }
};

export default Logo;
