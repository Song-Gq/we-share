package com.tongji.weshare.controller;

import com.alibaba.fastjson.JSON;
import com.tongji.weshare.entity.Answer;
import com.tongji.weshare.entity.Question;
import com.tongji.weshare.model.ResponseModel;
import com.tongji.weshare.service.AnswerService;
import com.tongji.weshare.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SubmitController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public SubmitController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/submitquestion")
    public String submitQuestion(Integer postingId, String content, Integer questionerId) {
        String response;
        Question question = new Question();
        question.setContent(content);
        question.setPostId(postingId);
        question.setQuestionerId(questionerId);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        question.setTime(date);
        int insertQuestion =  questionService.newQuestion(question);
        ResponseModel responseModel = new ResponseModel(200);
        response = JSON.toJSONString(responseModel);
        return response;
    }
    @GetMapping("/submitanswer")
    public String submitAnswer(Integer questionId, String content, Integer answererId) {
        String response;
        Answer answer = new Answer();
        answer.setAnswererId(answererId);
        answer.setContent(content);
        answer.setQuestionId(questionId);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        answer.setTime(date);
        int insertAnswer = answerService.newAnswer(answer);
        ResponseModel responseModel = new ResponseModel(200);
        response = JSON.toJSONString(responseModel);
        return response;
    }

}
