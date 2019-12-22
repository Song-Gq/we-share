package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.weshare.entity.*;
import com.tongji.weshare.model.ResponseModel;
import com.tongji.weshare.service.PostService;
import com.tongji.weshare.service.TagService;
import com.tongji.weshare.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EditController {

    private final UserService userService;
    private final TagService tagService;
    private final PostService postService;

    public EditController(UserService userService, TagService tagService, PostService postService) {
        this.userService = userService;
        this.tagService = tagService;
        this.postService = postService;
    }

    @GetMapping(value = "/edit", params = {"title", "topics[]", "content", "posterId"})
    public String newPost(String title, @org.jetbrains.annotations.NotNull @RequestParam(value = "topics[]") String[] topics, String content, Integer posterId) {
        String response;

        Post post = new Post();
        post.setTitle(title);
        post.setPosterId(posterId);
        PostExample postExample = new PostExample();
        PostExample.Criteria criteriaPost = postExample.createCriteria();
        long countPosts = postService.countPosts(postExample);
        int insertPost = postService.newPost(post);
        PostExample postExample1 = new PostExample();
        postExample.setOrderByClause("post_id desc");
        PostExample.Criteria criteriaPost1 = postExample1.createCriteria();
        criteriaPost1.andPosterIdEqualTo(posterId);
        criteriaPost1.andTitleEqualTo(title);
        List<Post> posts1 = postService.getPosts(postExample);
        int postId = posts1.get(0).getPostId();
        PostContent postContent = new PostContent();
        postContent.setPostId(postId);
        postContent.setFloor(1);
        postContent.setContent(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        postContent.setTime(date);
        int insertPC = postService.newPostContent(postContent);

        int length = topics.length;
        for (int i = 0; i < length; i++) {
            Tag tag = new Tag();
            tag.setPostId(postId);
            tag.setTagName(topics[i]);
            int insertTag = tagService.newTagPostPair(tag);
        }

        ResponseModel responseModel = new ResponseModel(200);
        response = JSON.toJSONString(responseModel);
        return response;
    }

    @GetMapping(value = "/edit", params = {"postingId", "content"})
    public String newPost(Integer postingId, String content) {
        String response;

        PostContentExample postContentExample = new PostContentExample();
        PostContentExample.Criteria criteriaPC = postContentExample.createCriteria();
        criteriaPC.andPostIdEqualTo(postingId);
        long countFloors = postService.countPostContents(postContentExample);
        int floor = (int)countFloors + 1;
        PostContent postContent = new PostContent();
        postContent.setContent(content);
        postContent.setFloor(floor);
        postContent.setPostId(postingId);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        postContent.setTime(date);
        int insertPC = postService.newPostContent(postContent);

        ResponseModel responseModel = new ResponseModel(200);
        response = JSON.toJSONString(responseModel);
        return response;
    }
}
