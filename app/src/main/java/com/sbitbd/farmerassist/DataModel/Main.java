package com.sbitbd.farmerassist.DataModel;

import java.text.MessageFormat;

public class Main {
    double temp;
    double pressure;
    double humidity;
    double temp_min;
    double temp_max;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Main'{'temp={0}, pressure={1}, humidity={2}, temp_min={3}, " +
                "temp_max={4}'}'", temp, pressure, humidity, temp_min, temp_max);
    }
}
