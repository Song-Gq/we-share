package com.tongji.weshare.model;

import java.util.List;

public class PostListModel {
    private List<PostModel> list;

    public PostListModel(List<PostModel> list) {
        this.list = list;
    }

    public PostListModel() {}

    public void setList(List<PostModel> list) {
        this.list = list;
    }

    public List<PostModel> getList() {
        return list;
    }
}
