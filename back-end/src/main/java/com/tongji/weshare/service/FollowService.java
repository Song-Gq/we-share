package com.tongji.weshare.service;

import com.tongji.weshare.entity.Follow;
import com.tongji.weshare.entity.FollowExample;
import com.tongji.weshare.mapper.FollowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {
    private final FollowMapper followMapper;
    public FollowService(FollowMapper followMapper) {
        this.followMapper = followMapper;
    }

    public List<Follow> getFollows(FollowExample example) {
        return followMapper.selectByExample(example);
    }

    public int newFollow(Follow follow) {
        return followMapper.insert(follow);
    }

    public int deleteFollow(FollowExample example) {
        return followMapper.deleteByExample(example);
    }

    public long countFollows(FollowExample example) {
        return followMapper.countByExample(example);
    }
}
