package com.tongji.weshare.model;

import com.tongji.weshare.entity.PostContent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostingContentModel {
    private String time;
    private String content;
    private Integer postingId;
    private Integer floor;
    private String pic;

    public PostingContentModel(PostContent postContent) {
        this.content = postContent.getContent();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        this.time = dateFormat.format(postContent.getTime());
        this.postingId = postContent.getPostId();
        this.floor = postContent.getFloor();
        int num = (postingId + floor - 1) % 15 + 1;
        this.pic = "pic/" + num + ".jpg";
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }
}
