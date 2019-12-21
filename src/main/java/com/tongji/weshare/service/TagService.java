package com.tongji.weshare.service;

import com.tongji.weshare.entity.Tag;
import com.tongji.weshare.entity.TagExample;
import com.tongji.weshare.mapper.TagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagMapper tagMapper;
    public TagService(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    public List<Tag> getTags(TagExample example) {
        return tagMapper.selectByExample(example);
    }
}
