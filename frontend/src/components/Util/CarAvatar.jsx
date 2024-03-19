import React from "react";

const carImages = {
  "Red Bull": () => import("../../assets/images/cars/redbull.avif"),
  Ferrari: () => import("../../assets/images/cars/ferrari.avif"),
  Mercedes: () => import("../../assets/images/cars/mercedes.avif"),
  McLaren: () => import("../../assets/images/cars/mclaren.avif"),
  "Aston Martin": () => import("../../assets/images/cars/astonmartin.avif"),
  "Haas F1 Team": () => import("../../assets/images/cars/haas.avif"),
  Williams: () => import("../../assets/images/cars/williams.avif"),
  Sauber: () => import("../../assets/images/cars/kicksauber.avif"),
  "RB F1 Team": () => import("../../assets/images/cars/visarb.avif"),
  "Alpine F1 Team": () => import("../../assets/images/cars/alpine.avif"),
};

const CarAvatar = ({ teamName, size }) => {
  const [image, setImage] = React.useState(null);

  React.useEffect(() => {
    const loadImage = async () => {
      const imageModule = await carImages[teamName]();
      setImage(imageModule.default);
    };

    loadImage();
  }, [teamName]);

  return (
    <img src={image} alt={teamName} style={{ width: size, height: "auto" }} />
  );
};

export default CarAvatar;
