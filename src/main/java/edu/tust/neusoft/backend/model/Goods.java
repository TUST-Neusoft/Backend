package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "goods_no")
    private String goodsNo;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "goods_introduce")
    private String goodsIntroduce;

    @Column(name = "goods_content")
    private String goodsContent;

    @Column(name = "goods_state")
    private int goodsState;

    @Column(name = "goods_picture")
    private String goodsPicture;

    @Column(name = "goods_market_price")
    private Double goodsMarketPrice;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
