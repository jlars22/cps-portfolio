import React from "react";
import { Link } from "react-router-dom";
import f1logo from "../assets/images/f1logo.png";
import Tooltip from "@mui/material/Tooltip";

const TopBar = ({ links }) => {
  return (
    <div className="flex items-center justify-between bg-gray-700 p-2">
      <Link to="/" className="flex items-center">
        <img
          src={f1logo}
          alt="F1 Logo"
          className="ml-2 h-10 transform transition duration-300 ease-in-out hover:scale-105"
        />
      </Link>
      <div className="flex items-center">
        {links.slice(1).map((link, index) => (
          <Link key={index} to={link.path} className="mr-2 text-white">
            <Tooltip title={link.label} placement="bottom" arrow>
              <div className="flex h-10 w-20 items-center justify-center rounded-3xl bg-gray-600 shadow-md transition-all duration-300 ease-in-out hover:scale-105 hover:rounded-xl hover:bg-red-600">
                <span className="flex items-center justify-center text-2xl text-gray-300 hover:text-white">
                  {link.logo}
                </span>
              </div>
            </Tooltip>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default TopBar;
