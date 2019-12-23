package com.tongji.weshare.model;

import java.util.List;

public class AnswerListModel {
    private List<AnswerModel> list;

    public AnswerListModel(List<AnswerModel> list) {
        this.list = list;
    }

    public AnswerListModel() {}

    public List<AnswerModel> getList() {
        return list;
    }

    public void setList(List<AnswerModel> list) {
        this.list = list;
    }
}
