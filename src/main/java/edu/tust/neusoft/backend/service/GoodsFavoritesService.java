package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface GoodsFavoritesService {
    Result countFavoritesByGoodsNo(String goodsNo);
    Result getFavoritesByUserId(int userId);
}
