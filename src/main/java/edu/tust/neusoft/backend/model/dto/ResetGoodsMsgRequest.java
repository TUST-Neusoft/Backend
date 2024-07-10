package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetGoodsMsgRequest {
    @JsonProperty("store_no")
    private String storeNo;
    @JsonProperty("goods_no")
    private String goodsNo;
    @JsonProperty("goods_stock")
    private int goodsStock;
    @JsonProperty("goods_price")
    private double goodsPrice;
}
