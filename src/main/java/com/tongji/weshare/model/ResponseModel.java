package com.tongji.weshare.model;

public class ResponseModel {
    private int response;
    public ResponseModel(int response) {
        this.response = response;
    }

    public ResponseModel() {};

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
