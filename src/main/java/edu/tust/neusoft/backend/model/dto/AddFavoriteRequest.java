package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddFavoriteRequest {

    @JsonProperty("goods_no")
    private String goodsNo;

    @JsonProperty("user_id")
    private int userId;

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
