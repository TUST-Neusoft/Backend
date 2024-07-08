package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_subtype")
    private String paymentSubtype;

    @Column(name = "delivery_type")
    private String deliveryType;

    @Column(name = "order_state")
    private String orderState;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
