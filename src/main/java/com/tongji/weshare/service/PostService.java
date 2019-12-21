package com.tongji.weshare.service;

import com.tongji.weshare.entity.Post;
import com.tongji.weshare.entity.PostContent;
import com.tongji.weshare.entity.PostContentExample;
import com.tongji.weshare.entity.PostExample;
import com.tongji.weshare.mapper.PostContentMapper;
import com.tongji.weshare.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;

    public PostService(PostMapper postMapper, PostContentMapper postContentMapper) {
        this.postMapper = postMapper;
        this.postContentMapper = postContentMapper;
    }

    public List<Post> getPosts(PostExample postExample) {
        return postMapper.selectByExample(postExample);
    }

    public List<PostContent> getPostContents(PostContentExample postContentExample) {
        return postContentMapper.selectByExampleWithBLOBs(postContentExample);
    }


}
