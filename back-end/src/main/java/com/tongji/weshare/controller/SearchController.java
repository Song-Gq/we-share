package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.tongji.weshare.entity.*;
import com.tongji.weshare.model.*;
import com.tongji.weshare.service.PostService;
import com.tongji.weshare.service.TagService;
import com.tongji.weshare.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    private final UserService userService;
    private final TagService tagService;
    private final PostService postService;

    public SearchController(UserService userService, TagService tagService, PostService postService) {
        this.userService = userService;
        this.tagService = tagService;
        this.postService = postService;
    }


    @GetMapping("/search")
    public String search(String type, String text, Integer page) {
        String response = "";
        if (type.equals("user")) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUsernameLike("%"+text+"%");
            PageHelper.startPage(page, 5);
            List<User> users = userService.getUsers(userExample);
            int size = users.size();
            List<UserModel> list = new ArrayList<UserModel>();
            for (int i = 0; i < size; i++) {
                UserModel userModel = new UserModel(users.get(i));
                list.add(userModel);
            }
            UserListModel userListModel = new UserListModel(list);
            response = JSON.toJSONString(userListModel);
        }
        else if (type.equals("topic")) {
            TagExample tagExample = new TagExample();
            TagExample.Criteria criteria = tagExample.createCriteria();
            tagExample.setDistinct(true);
            criteria.andTagNameLike("%"+text+"%");
            PageHelper.startPage(page, 50);
            List<Tag> tags = tagService.getTags(tagExample);
            int size = tags.size();
            List<TagModel> list = new ArrayList<TagModel>();
            for (int i = 0; i < size; i++) {
                TagModel tagModel = new TagModel(tags.get(i));
                if (!list.contains(tagModel))
                    list.add(tagModel);
            }
            TagListModel tagListModel = new TagListModel(list);

            response = JSON.toJSONString(tagListModel);
        }
        else if (type.equals("posting")) {
            PostExample postExample = new PostExample();
            PostExample.Criteria criteriaPost = postExample.createCriteria();

            PageHelper.startPage(page, 5);
            criteriaPost.andTitleLike("%"+text+"%");
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
        else if (type.equals("postingbytag")){
            TagExample tagExample = new TagExample();
            TagExample.Criteria criteriaTag = tagExample.createCriteria();
            PageHelper.startPage(page, 5);
            criteriaTag.andTagNameEqualTo(text);
            List<Tag> tags = tagService.getTags(tagExample);
            int size = tags.size();
            List<PostModel> list = new ArrayList<PostModel>();
            for (int i = 0; i < size; i++) {
                int postId = tags.get(i).getPostId();
                PostExample postExample = new PostExample();
                PostExample.Criteria criteriaPost = postExample.createCriteria();
                criteriaPost.andPostIdEqualTo(postId);
                List<Post> posts = postService.getPosts(postExample);
                int posterId = posts.get(0).getPosterId();
                PostContentExample postContentExample = new PostContentExample();
                PostContentExample.Criteria criteriaPC = postContentExample.createCriteria();
                criteriaPC.andPostIdEqualTo(postId).andFloorEqualTo(1);
                List<PostContent> postContents = postService.getPostContents(postContentExample);
                UserExample userExample = new UserExample();
                UserExample.Criteria criteriaUser = userExample.createCriteria();
                criteriaUser.andUserIdEqualTo(posterId);
                List<User> users = userService.getUsers(userExample);
                TagExample tagExample1 = new TagExample();
                TagExample.Criteria criteriaTag1 = tagExample1.createCriteria();
                criteriaTag1.andPostIdEqualTo(postId);
                List<Tag> tags1 = tagService.getTags(tagExample1);
                PostModel postModel =
                        new PostModel(posts.get(0), postContents.get(0), tags1, users.get(0));
                list.add(postModel);
            }
            PostListModel postListModel = new PostListModel(list);
            response = JSON.toJSONString(postListModel);
        }

        return response;
    }

}
