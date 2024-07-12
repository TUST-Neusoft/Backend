package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResetGoodsRequest {
    @JsonProperty("goods_id")
    private Integer goodsId;
    @JsonProperty("goods")
    private List<GoodsDto> goods;

    @Getter
    @Setter
    public static class GoodsDto {
        @JsonProperty("goods_name")
        private String goodsName;
        @JsonProperty("goods_no")
        private String goodsNo;
        @JsonProperty("category_id")
        private int categoryId;
        @JsonProperty("goods_introduce")
        private String goodsIntroduce;
        @JsonProperty("goods_content")
        private String goodsContent;
        @JsonProperty("goods_state")
        private int goodsState;
        @JsonProperty("goods_picture")
        private String goodsPicture;
        @JsonProperty("goods_market_price")
        private double goodsMarketPrice;

    }
}
