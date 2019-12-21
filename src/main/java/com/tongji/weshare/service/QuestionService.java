package com.tongji.weshare.service;

import com.tongji.weshare.entity.Question;
import com.tongji.weshare.entity.QuestionExample;
import com.tongji.weshare.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionMapper questionMapper;
    public QuestionService(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public List<Question> getQuestions(QuestionExample example) {
        return questionMapper.selectByExampleWithBLOBs(example);
    }
}
