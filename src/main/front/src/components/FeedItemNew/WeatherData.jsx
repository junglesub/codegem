import React, { useEffect, useState } from "react";

import "./WeatherData.css";

function WeatherData() {
  const [weatherInfo, setWeatherInfo] = useState();
  useEffect(() => {
    const url = `https://api.openweathermap.org/data/2.5/weather?lat=${36.103268}&lon=${129.388611}&appid=${
      import.meta.env.VITE_WEATHER_KEY
    }&units=metric`;
    fetch(url).then((doc) => doc.json().then((json) => setWeatherInfo(json)));
  }, []);

  return (
    <div className="FeedItem">
      <div className="weather">
        {!!weatherInfo?.weather ? (
          <b>
            지금 한동 날씨는:{" "}
            <img
              alt="Weather"
              src={`http://openweathermap.org/img/wn/${weatherInfo.weather[0].icon}.png`}
            />{" "}
            {weatherInfo.main.temp}°C
          </b>
        ) : (
          "Loading..."
        )}
      </div>
    </div>
  );
}

export default WeatherData;
