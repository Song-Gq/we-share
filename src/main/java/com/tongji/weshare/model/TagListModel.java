package com.tongji.weshare.model;

import java.util.List;

public class TagListModel {
    List<TagModel> list;

    public TagListModel(List<TagModel> list) {
        this.list = list;
    }

    public TagListModel() {}

    public List<TagModel> getList() {
        return list;
    }

    public void setList(List<TagModel> list) {
        this.list = list;
    }
}
