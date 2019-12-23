package com.tongji.weshare.model;

import java.util.List;

public class UserListModel {
    private List<UserModel> list;

    public UserListModel(List<UserModel> list) {
        this.list = list;
    }

    public UserListModel() {}

    public void setList(List<UserModel> list) {
        this.list = list;
    }

    public List<UserModel> getList() {
        return list;
    }
}
