package edu.tust.neusoft.backend.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SpecialGoodsResponse {
    private Long id;
    private Long specialId;
    private String goodsNo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Goods table fields
    private String goodsName;
    private int categoryId;
    private String goodsIntroduce;
    private String goodsContent;
    private int goodsState;
    private String goodsPicture;
    private Double goodsMarketPrice;
    private LocalDateTime goodsCreateTime;
    private LocalDateTime goodsUpdateTime;
}
