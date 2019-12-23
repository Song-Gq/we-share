package com.tongji.weshare.model;

public class UserInfoModel {
    private UserModel userInfo;

    public UserInfoModel(UserModel userModel) {
        this.userInfo = userModel;
    }

    public UserInfoModel() {}

    public UserModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserModel userInfo) {
        this.userInfo = userInfo;
    }
}
