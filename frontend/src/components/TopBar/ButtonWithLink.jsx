import React from "react";
import { Link } from "react-router-dom";

const ButtonWithLink = ({ link }) => {
  return (
    <Link to={link.path} className="mr-2 text-white">
      <div
        className="flex h-10 w-20 items-center justify-center rounded-3xl bg-gray-800 shadow-md 
                     transition-all duration-300 ease-in-out hover:scale-105 hover:rounded-xl hover:bg-red-600"
      >
        <span className="flex items-center justify-center text-2xl text-gray-300 hover:text-white">
          {link.logo}
        </span>
      </div>
    </Link>
  );
};

export default ButtonWithLink;
