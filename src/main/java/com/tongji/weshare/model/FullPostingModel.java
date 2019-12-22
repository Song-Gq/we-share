package com.tongji.weshare.model;

import java.util.List;

public class FullPostingModel {
    private PostingHeadModel posting;
    private List<PostingContentModel> list;

    public FullPostingModel(PostingHeadModel posting, List<PostingContentModel> list) {
        this.posting = posting;
        this.list = list;
    }

    public FullPostingModel() {}

    public void setList(List<PostingContentModel> list) {
        this.list = list;
    }

    public void setPosting(PostingHeadModel posting) {
        this.posting = posting;
    }

    public List<PostingContentModel> getList() {
        return list;
    }

    public PostingHeadModel getPosting() {
        return posting;
    }
}
