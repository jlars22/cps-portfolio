import React from "react";

const driverImages = {
  VER: () => import("../../assets/images/drivers/ver.avif"),
};

const DriverAvatar = ({ driverCode, size }) => {
  const [image, setImage] = React.useState(null);

  React.useEffect(() => {
    const loadImage = async () => {
      const imageModule = await driverImages[driverCode]();
      setImage(imageModule.default);
    };

    loadImage();
  }, [driverCode]);

  return (
    <img src={image} alt={driverCode} style={{ width: size, height: "auto" }} />
  );
};

export default DriverAvatar;
