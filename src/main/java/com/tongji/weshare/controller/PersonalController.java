package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.tongji.weshare.entity.*;
import com.tongji.weshare.model.*;
import com.tongji.weshare.service.*;
import javafx.geometry.Pos;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonalController {
    private final UserService userService;
    private final PostService postService;
    private final TagService tagService;
    private final FollowService followService;
    private final FavoriteService favoriteService;

    public PersonalController(UserService userService, PostService postService, TagService tagService, FollowService followService, FavoriteService favoriteService) {
        this.userService = userService;
        this.postService = postService;
        this.tagService = tagService;
        this.followService = followService;
        this.favoriteService = favoriteService;
    }

    @GetMapping(value = "/personalPage", params = "userId")
    public String getPersonalPage(Integer userId) {
        String response;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<User> users = userService.getUsers(userExample);
        UserModel userModel = new UserModel(users.get(0));
        UserInfoModel userInfoModel = new UserInfoModel(userModel);
        response = JSON.toJSONString(userInfoModel);
        return response;
    }

    @GetMapping("changeInfo")
    public String changeInfo(Integer userId, String avatarUrl, String userName,
                             Byte gender, String email, String introduction) {
        String response;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<User> users = userService.getUsers(userExample);
        ResponseUserModel responseUserModel = new ResponseUserModel();
        if (users.size()==1) {
            UserExample userExample1 = new UserExample();
            UserExample.Criteria criteria1 = userExample1.createCriteria();
            criteria1.andEmailEqualTo(email);
            long count = userService.countUsers(userExample1);
            if (count == 0) {
                User user = new User();
                user.setAvatarUrl(avatarUrl);
                user.setUserId(userId);
                user.setEmail(email);
                user.setGender(gender);
                user.setUsername(userName);
                user.setIntroduction(introduction);
                int updateUser = userService.updateUser(user);
                responseUserModel.setIsSuccess(0);
            }
        }
        response = JSON.toJSONString(responseUserModel);
        return response;
    }

    @GetMapping(value = "/personalPage", params = {"userId", "searchType", "page"})
    public String getMy(Integer userId, @NotNull String searchType, Integer page) {
        String response = "";
        if (searchType.equals("myFavorite")) {
            FavoriteExample favoriteExample = new FavoriteExample();
            FavoriteExample.Criteria criteriaFa = favoriteExample.createCriteria();
            criteriaFa.andUserIdEqualTo(userId);
            PageHelper.startPage(page, 5);
            List<Favorite> favorites = favoriteService.getFavorites(favoriteExample);
            int size = favorites.size();
            List<PostModel> list = new ArrayList<PostModel>();
            for (int i = 0; i < size; i++) {
                int postId = favorites.get(i).getPostId();
                PostExample postExample = new PostExample();
                PostExample.Criteria criteriaPost = postExample.createCriteria();
                criteriaPost.andPostIdEqualTo(postId);
                List<Post> posts = postService.getPosts(postExample);
                int posterId = posts.get(0).getPosterId();
                PostContentExample postContentExample = new PostContentExample();
                PostContentExample.Criteria criteriaPC = postContentExample.createCriteria();
                criteriaPC.andPostIdEqualTo(postId);
                criteriaPC.andFloorEqualTo(1);
                List<PostContent> postContents = postService.getPostContents(postContentExample);
                TagExample tagExample = new TagExample();
                TagExample.Criteria criteriaTag = tagExample.createCriteria();
                criteriaTag.andPostIdEqualTo(postId);
                List<Tag> tags = tagService.getTags(tagExample);
                UserExample userExample = new UserExample();
                UserExample.Criteria criteriaUser = userExample.createCriteria();
                criteriaUser.andUserIdEqualTo(posterId);
                List<User> users = userService.getUsers(userExample);
                PostModel postModel =
                        new PostModel(posts.get(0), postContents.get(0), tags, users.get(0));
                list.add(postModel);
            }
            PostListModel postListModel = new PostListModel(list);
            response = JSON.toJSONString(postListModel);
        }
        else if (searchType.equals("myPost")) {
            PostExample postExample = new PostExample();
            PostExample.Criteria criteriaPost = postExample.createCriteria();
            criteriaPost.andPosterIdEqualTo(userId);
            PageHelper.startPage(page, 5);
            List<Post> posts = postService.getPosts(postExample);
            int size = posts.size();
            List<PostModel> list = new ArrayList<PostModel>();
            for (int i = 0; i < size; i++) {
                int postId = posts.get(i).getPostId();
                int posterId = posts.get(i).getPosterId();
                PostContentExample postContentExample = new PostContentExample();
                PostContentExample.Criteria criteriaPC = postContentExample.createCriteria();
                criteriaPC.andPostIdEqualTo(postId);
                criteriaPC.andFloorEqualTo(1);
                List<PostContent> postContents = postService.getPostContents(postContentExample);
                TagExample tagExample = new TagExample();
                TagExample.Criteria criteriaTag = tagExample.createCriteria();
                criteriaTag.andPostIdEqualTo(postId);
                List<Tag> tags = tagService.getTags(tagExample);
                UserExample userExample = new UserExample();
                UserExample.Criteria criteriaUser = userExample.createCriteria();
                criteriaUser.andUserIdEqualTo(posterId);
                List<User> users = userService.getUsers(userExample);
                PostModel postModel =
                        new PostModel(posts.get(i), postContents.get(0), tags, users.get(0));
                list.add(postModel);

            }
            PostListModel postListModel = new PostListModel(list);
            response = JSON.toJSONString(postListModel);
        }
        else if (searchType.equals("myFocus")) {
            FollowExample followExample = new FollowExample();
            FollowExample.Criteria criteriaFo = followExample.createCriteria();
            criteriaFo.andFollowerIdEqualTo(userId);
            PageHelper.startPage(page, 5);
            List<Follow> follows = followService.getFollows(followExample);
            int size = follows.size();
            List<UserModel> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                int followedId = follows.get(i).getFollowedUserId();
                UserExample userExample = new UserExample();
                UserExample.Criteria criteriaU = userExample.createCriteria();
                criteriaU.andUserIdEqualTo(followedId);
                List<User> users = userService.getUsers(userExample);
                UserModel userModel = new UserModel(users.get(0));
                list.add(userModel);
            }
            UserListModel userListModel = new UserListModel(list);
            response = JSON.toJSONString(userListModel);
        }

        return response;
    }
}
