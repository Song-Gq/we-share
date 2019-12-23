package com.tongji.weshare.model;

public class ResponseUserModel {
    private Integer isSuccess;
    private Integer userId;
    private String userName;

    public ResponseUserModel() {
        isSuccess = 1;
        userId = -1;
        userName = "";
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getIsSuccess() {
        return isSuccess;
    }
}
