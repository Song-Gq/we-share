package com.tongji.weshare.model;

import com.tongji.weshare.entity.Question;
import com.tongji.weshare.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuestionModel {
    private Integer questionId;
    private String time;
    private String content;
    private Integer questionerId;
    private String avatarDir;

    public QuestionModel(Question question, User user) {
        this.avatarDir = user.getAvatarUrl();
        this.content = question.getContent();
        this.questionerId = question.getQuestionerId();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        this.time = dateFormat.format(question.getTime());
        this.questionId = question.getQuestionId();
    }

    public QuestionModel() {}


    public String getContent() {
        return content;
    }

    public Integer getQuestionerId() {
        return questionerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public String getAvatarDir() {
        return avatarDir;
    }

    public String getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAvatarDir(String avatarDir) {
        this.avatarDir = avatarDir;
    }

    public void setQuestionerId(Integer questionerId) {
        this.questionerId = questionerId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
