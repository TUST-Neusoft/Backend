package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.ResetGoodsPriceAndStorageRequest;
import edu.tust.neusoft.backend.response.Result;

public interface GoodsStoreService {
    Result getGoodsByStoreNo(String storeNo);
    Result resetGoodsPriceAndStorage(ResetGoodsPriceAndStorageRequest resetGoodsPriceAndStorageRequest);
}
