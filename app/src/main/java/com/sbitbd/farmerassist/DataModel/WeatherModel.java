package com.sbitbd.farmerassist.DataModel;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class WeatherModel {
    Coord coord;
    Main main;
    sys sys;
    List<Weather> weather = new ArrayList<>();
    String name;

    public Coord getCoord() {
        return coord;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }

    public sys getSys() {
        return sys;
    }

    @Override
    public String toString() {
        return MessageFormat.format("WeatherModel'{'coord={0}, main={1}, weather={2}, name=''{3}'''}'",
                coord.toString(), main.toString(), weather.get(0).toString(), name);
    }
}
