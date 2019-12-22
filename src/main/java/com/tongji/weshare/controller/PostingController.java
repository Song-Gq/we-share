package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import com.tongji.weshare.entity.*;
import com.tongji.weshare.model.*;
import com.tongji.weshare.service.*;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostingController {
    private final UserService userService;
    private final TagService tagService;
    private final PostService postService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    public PostingController(UserService userService, TagService tagService,
                             PostService postService, QuestionService questionService,
                             AnswerService answerService) {
        this.userService = userService;
        this.tagService = tagService;
        this.postService = postService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/posting/question")
    public String getPostingOrQuestion(Integer postingId, Integer postingPage, Integer questionPage) {
        String response = "";
        if (questionPage == 0) {
            PostExample postExample = new PostExample();
            PostContentExample postContentExample = new PostContentExample();
            UserExample userExample = new UserExample();
            TagExample tagExample = new TagExample();

            PostExample.Criteria criteriaPost = postExample.createCriteria();
            PostContentExample.Criteria criteriaPC = postContentExample.createCriteria();
            UserExample.Criteria criteriaUser = userExample.createCriteria();
            TagExample.Criteria criteriaTag = tagExample.createCriteria();

            criteriaPost.andPostIdEqualTo(postingId);
            List<Post> posts = postService.getPosts(postExample);
            Integer posterId = posts.get(0).getPosterId();
            criteriaUser.andUserIdEqualTo(posterId);
            List<User> users = userService.getUsers(userExample);
            criteriaTag.andPostIdEqualTo(postingId);
            List<Tag> tags = tagService.getTags(tagExample);

            PageHelper.startPage(postingPage, 5);
            postContentExample.setOrderByClause("floor asc");
            criteriaPC.andPostIdEqualTo(postingId);
            List<PostContent> postContents = postService.getPostContents(postContentExample);

            PostingHeadModel postingHeadModel = new PostingHeadModel(posts.get(0), tags, users.get(0));

            int size = postContents.size();
            List<PostingContentModel> list = new ArrayList<PostingContentModel>();
            for (int i = 0; i < size; i++) {
                PostingContentModel postingContentModel = new PostingContentModel(postContents.get(i));
                list.add(postingContentModel);
            }
            FullPostingModel fullPostingModel = new FullPostingModel(postingHeadModel, list);
            response = JSON.toJSONString(fullPostingModel);
        }
        else if (postingPage==0) {
            QuestionExample questionExample = new QuestionExample();
            QuestionExample.Criteria criteriaQ = questionExample.createCriteria();
            criteriaQ.andPostIdEqualTo(postingId);
            PageHelper.startPage(questionPage, 5);
            List<Question> questions = questionService.getQuestions(questionExample);

            List<QuestionModel> list = new ArrayList<QuestionModel>();
            int size = questions.size();
            for (int i = 0; i < size; i++) {
                UserExample userExample = new UserExample();
                UserExample.Criteria criteriaU = userExample.createCriteria();
                criteriaU.andUserIdEqualTo(questions.get(i).getQuestionerId());
                List<User> users = userService.getUsers(userExample);
                QuestionModel questionModel = new QuestionModel(questions.get(i), users.get(0));
                list.add(questionModel);
            }
            QuestionListModel questionListModel = new QuestionListModel(list);
            response = JSON.toJSONString(questionListModel);
        }
        return response;
    }

    @GetMapping("/posting/answer")
    public String getAnswer(Integer questionId, Integer answerPage) {
        String response;
        AnswerExample answerExample = new AnswerExample();
        AnswerExample.Criteria criteriaA = answerExample.createCriteria();
        criteriaA.andQuestionIdEqualTo(questionId);
        PageHelper.startPage(answerPage, 5);
        List<Answer> answers = answerService.getAnswers(answerExample);
        List<AnswerModel> list = new ArrayList<AnswerModel>();

        int size = answers.size();
        for (int i = 0; i < size; i++) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteriaU = userExample.createCriteria();
            criteriaU.andUserIdEqualTo(answers.get(i).getAnswererId());
            List<User> users = userService.getUsers(userExample);
            AnswerModel answerModel = new AnswerModel(answers.get(i), users.get(0));
            list.add(answerModel);
        }
        AnswerListModel answerListModel = new AnswerListModel(list);
        response = JSON.toJSONString(answerListModel);
        return response;
    }
}
