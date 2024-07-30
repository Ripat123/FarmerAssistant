package com.sbitbd.farmerassist.DataModel;

import java.text.MessageFormat;

public class Coord {
    double lon;
    double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Coord'{'lon={0}, lat={1}'}'", lon, lat);
    }
}
