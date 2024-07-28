package com.sbitbd.farmerassist.utils;

public class Utils {
    public static final String METRIC = "metric";
    public static final String IMPERIAL = "imperial";
    public static final String BASE_URL = "https://api.openweathermap.org";

    public static String getTempString(double temp){
        return round(temp) +" °C";
    }

    public static String getHumidityString(double hum){
        return round(hum) +"%";
    }

    public static String getPressureString(double press){
        return round(press) +" hPa";
    }

    public static int round(double value) {
        return (int) Math.round(value);
    }
}
