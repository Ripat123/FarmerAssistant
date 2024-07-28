package com.sbitbd.farmerassist.DataModel;

public class WeatherModel {
    Coord coord;
    Main main;
    Sys sys;
    String name;
    int id;

    private class Coord {
        double lon;
        double lat;
    }

    public class Main {
        double temp;
        double pressure;
        double humidity;
        double temp_min;
        double temp_max;
    }

    private class Sys {
        String country;
    }
}
