package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.GoodsFavorites;
import edu.tust.neusoft.backend.repository.GoodsFavoritesRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.GoodsFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsFavoritesServiceImpl implements GoodsFavoritesService {
    @Autowired
    private GoodsFavoritesRepository goodsFavoritesRepository;

    @Override
    public Result countFavoritesByGoodsNo(String goodsNo) {
        long count = goodsFavoritesRepository.countByGoodsNo(goodsNo);
        return Result.success("获取成功", count);
    }

    @Override
    public Result getFavoritesByUserId(int userId) {
        List<GoodsFavorites> favoritesList = goodsFavoritesRepository.findByUserId(userId);
        if (favoritesList.isEmpty()) {
            return Result.fail("获取失败");
        }
        return Result.success("获取成功", favoritesList);
    }

    @Override
    public Result addFavorite(GoodsFavorites goodsFavorites) {
        goodsFavorites.setCreateTime(LocalDateTime.now());
        goodsFavoritesRepository.save(goodsFavorites);
        return Result.success("添加成功", null);
    }

    @Override
    public boolean existsById(int id) {
        return goodsFavoritesRepository.existsById(id);
    }

    @Override
    public void deleteById(int id) {
        goodsFavoritesRepository.deleteById(id);
    }
}
