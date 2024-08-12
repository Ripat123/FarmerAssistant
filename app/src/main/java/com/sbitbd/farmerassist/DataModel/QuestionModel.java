package com.sbitbd.farmerassist.DataModel;

import java.io.Serializable;

public class QuestionModel implements Serializable {
    String id,title,agro_id;

    public QuestionModel(String id, String title, String agro_id) {
        this.id = id;
        this.title = title;
        this.agro_id = agro_id;
    }

    public QuestionModel() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAgro_id() {
        return agro_id;
    }
}
