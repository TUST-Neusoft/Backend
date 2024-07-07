package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.GoodsFavorites;
import edu.tust.neusoft.backend.response.Result;

public interface GoodsFavoritesService {
    Result countFavoritesByGoodsNo(String goodsNo);
    Result getFavoritesByUserId(int userId);
    Result addFavorite(GoodsFavorites goodsFavorites);
    boolean existsById(int id);  // 检查收藏记录是否存在
    void deleteById(int id);  // 删除收藏记录
}
