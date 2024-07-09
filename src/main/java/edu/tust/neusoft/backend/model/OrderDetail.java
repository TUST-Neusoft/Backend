package edu.tust.neusoft.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Setter
@Getter
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "store_no", nullable = false)
    private String storeNo;

    @Column(name = "goods_no", nullable = false)
    private String goodsNo;

    @Column(name = "goods_amount", nullable = false)
    private int goodsAmount;

    @Column(name = "goods_price", nullable = false)
    private double goodsPrice;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
