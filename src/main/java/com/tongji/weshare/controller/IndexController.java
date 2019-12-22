package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.tongji.weshare.entity.*;
import com.tongji.weshare.model.PostListModel;
import com.tongji.weshare.model.PostModel;
import com.tongji.weshare.model.TagListModel;
import com.tongji.weshare.model.TagModel;
import com.tongji.weshare.service.PostService;
import com.tongji.weshare.service.TagService;
import com.tongji.weshare.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class IndexController {
    private final UserService userService;
    private final TagService tagService;
    private final PostService postService;

    public IndexController(UserService userService, TagService tagService, PostService postService) {
        this.userService = userService;
        this.tagService = tagService;
        this.postService = postService;
    }

    @GetMapping("/index")
    public String index(String type, Integer page) {
        String response = "";

        if (type.equals("posting")) {
            PostExample postExample = new PostExample();
            PostExample.Criteria criteriaPost = postExample.createCriteria();
            postExample.setOrderByClause("star_num desc");

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
        else if (type.equals("topic")) {

            TagExample tagExample1 = new TagExample();
            TagExample.Criteria criteriaTag1 = tagExample1.createCriteria();
            List<Tag> tags = tagService.getTags(tagExample1);
            int size1 = tags.size();
            List<TagModel> list1 = new ArrayList<TagModel>();

            for (int i = 0; i < size1; i++) {
                TagModel tagModel = new TagModel(tags.get(i));
                if (!list1.contains(tagModel))
                    list1.add(tagModel);
            }

            int size2 = list1.size();
            for (int i = 0; i < size2; i++) {
                TagExample tagExample2 = new TagExample();
                TagExample.Criteria criteriatag2 = tagExample2.createCriteria();
                criteriatag2.andTagNameEqualTo(list1.get(i).getTagName());
                list1.get(i).setCounts(tagService.countTags(tagExample2));
            }
            Collections.sort(list1);

            int pageLimit = 2;
            int maxPage = (int)Math.ceil((double) list1.size() / pageLimit);
            if (page > maxPage)
                page = maxPage;
            int startPage = pageLimit * page - pageLimit;
            int endPage = pageLimit * page;
            if (endPage > list1.size())
                endPage = list1.size();
            List<TagModel> list2 = list1.subList(startPage, endPage);
            TagListModel tagListModel = new TagListModel(list2);
            response = JSON.toJSONString(tagListModel);

        }
        return response;
    }

}
