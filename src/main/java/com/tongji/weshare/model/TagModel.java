package com.tongji.weshare.model;

import com.tongji.weshare.entity.Tag;

import java.util.Objects;

public class TagModel {
    private String tagName;
    private String tagId;

    public TagModel(Tag tag) {
        this.tagName = tag.getTagName();
        this.tagId = this.tagName;
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
}
