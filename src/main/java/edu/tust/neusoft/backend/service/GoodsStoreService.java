package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.model.dto.ResetGoodsMsgRequest;
import edu.tust.neusoft.backend.response.Result;

public interface GoodsStoreService {
    Result getGoodsByStoreNo(String storeNo);
    Result resetGoodsMsg(ResetGoodsMsgRequest resetGoodsMsgRequest);
}
