package com.tongji.weshare.model;

import java.util.List;

public class QuestionListModel {
    private List<QuestionModel> list;

    public QuestionListModel(List<QuestionModel> list) {
        this.list = list;
    }

    public List<QuestionModel> getList() {
        return list;
    }

    public void setList(List<QuestionModel> list) {
        this.list = list;
    }
}
