package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.weshare.entity.Favorite;
import com.tongji.weshare.entity.FavoriteExample;
import com.tongji.weshare.entity.Post;
import com.tongji.weshare.entity.PostExample;
import com.tongji.weshare.model.ResponseModel;
import com.tongji.weshare.service.FavoriteService;
import com.tongji.weshare.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StarController {

    private final FavoriteService favoriteService;
    private final PostService postService;

    public StarController(FavoriteService favoriteService, PostService postService) {
        this.favoriteService = favoriteService;
        this.postService = postService;
    }

    @GetMapping("/star")
    public String newFavorite(Integer postingId, Integer userId) {
        String response;
        FavoriteExample favoriteExample = new FavoriteExample();
        FavoriteExample.Criteria criteriaF = favoriteExample.createCriteria();
        criteriaF.andUserIdEqualTo(userId);
        criteriaF.andPostIdEqualTo(postingId);
        long count = favoriteService.countFavorites(favoriteExample);
        ResponseModel responseModel = new ResponseModel(201);
        if (count == 0) {
            Favorite favorite = new Favorite();
            favorite.setPostId(postingId);
            favorite.setUserId(userId);
            int insertFavorite = favoriteService.newFavorite(favorite);
            PostExample postExample = new PostExample();
            PostExample.Criteria criteria = postExample.createCriteria();
            criteria.andPostIdEqualTo(postingId);
            List<Post> posts = postService.getPosts(postExample);
            int stars = posts.get(0).getStarNum();
            Post post = new Post();
            post.setPostId(posts.get(0).getPostId());
            post.setStarNum(stars + 1);
            int updatePost = postService.updatePost(post);
            responseModel.setResponse(200);
        }
        response = JSON.toJSONString(responseModel);
        return response;
    }
}
