package com.tongji.weshare.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.tongji.weshare.entity.Tag;

import java.util.Objects;

public class TagModel implements Comparable<TagModel> {
    private String tagName;
    private String tagId;

    @JSONField(serialize = false)
    private long counts;

    public TagModel(Tag tag) {
        this.tagName = tag.getTagName();
        this.tagId = this.tagName;
        counts = 0;
    }

    public TagModel() {};

    public void setTagName(String tagName) {
        this.tagName = tagName;
        this.tagId = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    public long getCounts() {
        return counts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagModel tagModel = (TagModel) o;
        return Objects.equals(tagName, tagModel.tagName) &&
                Objects.equals(tagId, tagModel.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, tagId);
    }

    @Override
    public int compareTo(TagModel o) {
        Long count1 = this.getCounts();
        Long count2 = o.getCounts();
        return count2.compareTo(count1);
    }
}
