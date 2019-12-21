package com.tongji.weshare.model;

import com.tongji.weshare.entity.Post;
import com.tongji.weshare.entity.PostContent;
import com.tongji.weshare.entity.Tag;
import com.tongji.weshare.entity.User;

import java.util.ArrayList;
import java.util.List;

public class PostModel {
    private int postingId;
    private String title;
    private String content;
    private String pic;
    private int views;
    private int stars;
    private List<TagModel> topic;
    private String avatar;
    private int userId;

    public PostModel(Post post, PostContent postContent, List<Tag> tags, User user) {
        this.postingId = post.getPosterId();
        this.title = post.getTitle();
        this.content = postContent.getContent();
        this.pic = "pic/1.jpg";
        this.views = post.getViews();
        this.stars = post.getStarNum();
        topic = new ArrayList<TagModel>();
        int size = tags.size();
        for (int i = 0; i < size; i++) {
            TagModel tagModel = new TagModel(tags.get(i));
            topic.add(tagModel);
        }
        avatar = user.getAvatarUrl();
        userId = user.getUserId();
    }

    public PostModel() {}

    public String getAvatar() {
        return avatar;
    }

    public int getPostingId() {
        return postingId;
    }

    public int getStars() {
        return stars;
    }

    public int getUserId() {
        return userId;
    }

    public int getViews() {
        return views;
    }

    public List<TagModel> getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public String getPic() {
        return pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTopic(List<TagModel> topic) {
        this.topic = topic;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setPostingId(int postingId) {
        this.postingId = postingId;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
