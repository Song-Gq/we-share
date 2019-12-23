package com.tongji.weshare.model;

import com.tongji.weshare.entity.User;


public class UserModel {
    private Integer userId;
    private String userName;
    private String avatar;
    private String email;
    private String introduction;
    private Byte gender;

    public UserModel(User user) {
        this.avatar = user.getAvatarUrl();
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.email = user.getEmail();
        this.introduction = user.getIntroduction();
        this.gender = user.getGender();
    }

    public UserModel() {}

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getGender() {
        return gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getUserName() {
        return userName;
    }
}
