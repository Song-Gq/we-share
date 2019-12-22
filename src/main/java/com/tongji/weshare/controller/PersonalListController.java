package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.weshare.entity.FollowExample;
import com.tongji.weshare.entity.PostExample;
import com.tongji.weshare.model.ResponseUserModel;
import com.tongji.weshare.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalListController {
    private final PostService postService;
    private final FollowService followService;

    public PersonalListController(UserService userService, PostService postService, TagService tagService, FavoriteService favoriteService, FollowService followService, QuestionService questionService, AnswerService answerService) {
        this.postService = postService;
        this.followService = followService;
    }

    @GetMapping(value = "/personalPage/personalList", params = {"userId", "followedId"})
    public String unFollow(Integer userId, Integer followedId) {
        String response;
        ResponseUserModel responseUserModel = new ResponseUserModel();
        FollowExample followExample = new FollowExample();
        FollowExample.Criteria criteria = followExample.createCriteria();
        criteria.andFollowerIdEqualTo(userId);
        criteria.andFollowedUserIdEqualTo(followedId);
        int deleteFollow = followService.deleteFollow(followExample);
        responseUserModel.setIsSuccess(0);
        response = JSON.toJSONString(responseUserModel);
        return response;
    }

    @GetMapping(value = "/personalPage/personalList", params = {"userId", "postingId"})
    public String deletePosting(Integer userId, Integer postingId) {
        String response;
        ResponseUserModel responseUserModel = new ResponseUserModel();
        PostExample postExample = new PostExample();
        PostExample.Criteria criteria = postExample.createCriteria();
        criteria.andPosterIdEqualTo(userId);
        criteria.andPostIdEqualTo(postingId);
        int deletePost = postService.deletePost(postExample);
        responseUserModel.setIsSuccess(0);
        response = JSON.toJSONString(responseUserModel);
        return response;
    }
}
