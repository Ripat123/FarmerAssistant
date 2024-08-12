package com.sbitbd.farmerassist.DataModel;

import java.io.Serializable;

public class AgroModel implements Serializable {
    String id, url,name;

    public AgroModel(String id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public AgroModel() {
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
