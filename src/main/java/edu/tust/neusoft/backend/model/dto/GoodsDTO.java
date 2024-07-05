package edu.tust.neusoft.backend.model.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class GoodsDTO {
    private Long id;
    private String goodsNo;
    private String goodsName;
    private int categoryId;
    private String goodsIntroduce;
    private String goodsContent;
    private int goodState;
    private String goodsPicture;
    private double goodsPrice;
    private Date createTime;
    private Date updateTime;

    // Getters and setters
}

