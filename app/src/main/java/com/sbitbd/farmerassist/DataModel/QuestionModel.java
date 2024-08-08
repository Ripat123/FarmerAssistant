package com.sbitbd.farmerassist.DataModel;

public class QuestionModel {
    String id,title,type;

    public QuestionModel(String id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
