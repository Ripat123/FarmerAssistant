package com.sbitbd.farmerassist.DataModel;

import java.io.Serializable;

public class DiseasesModel implements Serializable {
    String id, url,name,agro_id;

    public DiseasesModel(String id, String url, String name, String agro_id) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.agro_id = agro_id;
    }

    public DiseasesModel() {
    }

    public String getAgro_id() {
        return agro_id;
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
