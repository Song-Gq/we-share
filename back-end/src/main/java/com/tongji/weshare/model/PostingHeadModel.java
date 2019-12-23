package com.tongji.weshare.model;

import com.tongji.weshare.entity.Post;
import com.tongji.weshare.entity.Tag;
import com.tongji.weshare.entity.User;

import java.util.List;

public class PostingHeadModel {
    private String avatar;
    private String title;
    private String[] topic;
    private Integer posterId;

    public PostingHeadModel(Post post, List<Tag> tags, User user) {
        this.avatar = user.getAvatarUrl();
        this.title = post.getTitle();
        this.posterId = user.getUserId();
        int size = tags.size();
        topic = new String[size];
        for (int i = 0; i < size; i++) {
            topic[i] = tags.get(i).getTagName();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public String[] getTopic() {
        return topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public void setTopic(String[] topic) {
        this.topic = topic;
    }
}
