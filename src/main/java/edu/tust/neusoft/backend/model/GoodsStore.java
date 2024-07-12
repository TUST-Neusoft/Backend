package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "goods_store")
public class GoodsStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "store_no")
    private String storeNo;

    @Column(name = "goods_no")
    private String goodsNo;

    @Column(name = "goods_price")
    private Double goodsPrice;

    @Column(name = "goods_stock")
    private int goodsStock;

    @Column(name = "goods_type")
    private String goodsType;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
