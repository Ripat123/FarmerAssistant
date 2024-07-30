package com.sbitbd.farmerassist.DataModel;

import java.text.MessageFormat;

public class Weather {
    String main;
    String description;
    String icon;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Weather'{'main=''{0}'', description=''{1}'', icon=''{2}'''}'", main, description, icon);
    }
}
