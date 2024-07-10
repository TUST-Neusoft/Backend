package edu.tust.neusoft.backend.model.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDetailResponse {
    private int id;
    private String orderNo;
    private String storeNo;
    private String goodsNo;
    private int goodsAmount;
    private Double goodsPrice;
    private Double totalPrice;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

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
