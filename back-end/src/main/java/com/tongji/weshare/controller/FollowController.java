package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.weshare.entity.Follow;
import com.tongji.weshare.entity.FollowExample;
import com.tongji.weshare.model.ResponseModel;
import com.tongji.weshare.service.FollowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }


    @GetMapping("/follow")
    public String newFollow(Integer followedId, Integer followerId) {
        String response;
        ResponseModel responseModel = new ResponseModel(201);

        FollowExample followExample = new FollowExample();
        FollowExample.Criteria criteria = followExample.createCriteria();
        criteria.andFollowedUserIdEqualTo(followedId);
        criteria.andFollowerIdEqualTo(followerId);
        long count = followService.countFollows(followExample);

        if (count == 0) {

            Follow follow = new Follow();
            follow.setFollowedUserId(followedId);
            follow.setFollowerId(followerId);
            int insertFollow = followService.newFollow(follow);
            responseModel.setResponse(200);
        }
        response = JSON.toJSONString(responseModel);
        return response;
    }
}
