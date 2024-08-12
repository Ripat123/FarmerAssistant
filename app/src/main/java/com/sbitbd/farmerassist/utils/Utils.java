package com.sbitbd.farmerassist.utils;

public class Utils {
    public static final String METRIC = "metric";
    public static final String IMPERIAL = "imperial";
    public static final String AI_MODEL = "gemini-1.5-flash";
    public static final String QUESTION = "qust";
    public static final String DATA = "data";
    public static final String AGRO_MODEL = "AgroModel";
    public static final String DISEASES_MODEL = "DiseasesModel";
    public static final String QUESTION_MODEL = "QuestionModel";
    public static final String AGRO_ID = "agro_id";
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 555;
    public static final String BASE_URL = "https://api.openweathermap.org";
    public static final String ICON_URL = "http://openweathermap.org/img/w/";

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
