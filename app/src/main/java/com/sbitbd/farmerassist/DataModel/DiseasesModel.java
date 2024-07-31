package com.sbitbd.farmerassist.DataModel;

public class DiseasesModel {
    String url,name;

    public DiseasesModel(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
