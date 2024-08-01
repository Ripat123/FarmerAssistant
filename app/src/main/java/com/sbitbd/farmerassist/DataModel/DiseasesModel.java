package com.sbitbd.farmerassist.DataModel;

public class DiseasesModel {
    String id, url,name;

    public DiseasesModel(String id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
