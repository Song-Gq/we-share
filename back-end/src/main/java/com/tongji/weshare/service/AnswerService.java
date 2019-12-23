package com.tongji.weshare.service;

import com.tongji.weshare.entity.Answer;
import com.tongji.weshare.entity.AnswerExample;
import com.tongji.weshare.mapper.AnswerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerMapper answerMapper;
    public AnswerService(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    public List<Answer> getAnswers(AnswerExample example) {
        return answerMapper.selectByExampleWithBLOBs(example);
    }

    public int newAnswer(Answer answer) {
        return answerMapper.insertSelective(answer);
    }
}
