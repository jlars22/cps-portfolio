import { IoCalendar } from "react-icons/io5";
import { GiFullMotorcycleHelmet } from "react-icons/gi";
import { FaFlagCheckered } from "react-icons/fa6";
import { BsStopwatchFill } from "react-icons/bs";
import { FaCheckCircle } from "react-icons/fa";

const Logo = ({ type }) => {
  switch (type) {
    case "calendar":
      return <IoCalendar />;
    case "driverstandings":
      return <GiFullMotorcycleHelmet />;
    case "qualifying":
      return <BsStopwatchFill />;
    case "race":
      return <FaFlagCheckered />;
    case "practice":
      return <FaCheckCircle />;
    default:
      return null;
  }
};

export default Logo;
