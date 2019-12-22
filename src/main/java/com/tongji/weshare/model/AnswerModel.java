package com.tongji.weshare.model;

import com.tongji.weshare.entity.Answer;
import com.tongji.weshare.entity.User;

import java.text.SimpleDateFormat;

public class AnswerModel {
    private String time;
    private String content;
    private Integer answererId;
    private String avatarDir;

    public AnswerModel(Answer answer, User user) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        this.answererId = user.getUserId();
        this.avatarDir = user.getAvatarUrl();
        this.content = answer.getContent();
        this.time = dateFormat.format(answer.getTime());
    }

    public AnswerModel() {}

    public void setTime(String time) {
        this.time = time;
    }

    public void setAvatarDir(String avatarDir) {
        this.avatarDir = avatarDir;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswererId(Integer answererId) {
        this.answererId = answererId;
    }

    public String getTime() {
        return time;
    }

    public String getAvatarDir() {
        return avatarDir;
    }

    public String getContent() {
        return content;
    }

    public Integer getAnswererId() {
        return answererId;
    }
}
