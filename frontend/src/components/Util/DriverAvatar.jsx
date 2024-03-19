import React from "react";

const driverImages = {
  VER: () => import("../../assets/images/drivers/ver.avif"),
  PER: () => import("../../assets/images/drivers/per.avif"),
  LEC: () => import("../../assets/images/drivers/lec.avif"),
  RUS: () => import("../../assets/images/drivers/rus.avif"),
  PIA: () => import("../../assets/images/drivers/pia.avif"),
  SAI: () => import("../../assets/images/drivers/sai.avif"),
  ALO: () => import("../../assets/images/drivers/alo.avif"),
  NOR: () => import("../../assets/images/drivers/nor.avif"),
  HAM: () => import("../../assets/images/drivers/ham.avif"),
  BEA: () => import("../../assets/images/drivers/bea.avif"),
  HUL: () => import("../../assets/images/drivers/hul.avif"),
  STR: () => import("../../assets/images/drivers/str.avif"),
  ALB: () => import("../../assets/images/drivers/alb.avif"),
  ZHO: () => import("../../assets/images/drivers/zho.avif"),
  MAG: () => import("../../assets/images/drivers/mag.avif"),
  RIC: () => import("../../assets/images/drivers/ric.avif"),
  OCO: () => import("../../assets/images/drivers/oco.avif"),
  TSU: () => import("../../assets/images/drivers/tsu.avif"),
  SAR: () => import("../../assets/images/drivers/sar.avif"),
  BOT: () => import("../../assets/images/drivers/bot.avif"),
  GAS: () => import("../../assets/images/drivers/gas.avif"),
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
