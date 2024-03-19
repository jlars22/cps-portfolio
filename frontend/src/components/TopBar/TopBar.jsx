import { Link } from "react-router-dom";
import f1logo from "../../assets/images/f1logo.png";
import ButtonWithLink from "./ButtonWithLink";

const TopBar = ({ links }) => {
  return (
    <div className="flex items-center justify-between bg-gray-900 p-2 outline outline-red-600">
      <img src={f1logo} alt="F1 Logo" className="ml-2 h-10" />

      <div className="flex items-center">
        {links.map((link, index) => (
          <ButtonWithLink key={index} link={link} />
        ))}
      </div>
    </div>
  );
};

export default TopBar;
