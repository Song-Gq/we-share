package com.tongji.weshare.service;

import com.tongji.weshare.entity.Favorite;
import com.tongji.weshare.entity.FavoriteExample;
import com.tongji.weshare.mapper.FavoriteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteMapper favoriteMapper;
    public FavoriteService(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    public List<Favorite> getFavorites(FavoriteExample example) {
        return favoriteMapper.selectByExample(example);
    }

    public int newFavorite(Favorite favorite) {
        return favoriteMapper.insert(favorite);
    }
}
