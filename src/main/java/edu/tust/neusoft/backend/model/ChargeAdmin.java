package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "charge")
public class ChargeAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "charge_no")
    private String chargeNo;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "charge_name")
    private String chargeName;

    @Column(name = "charge_status")
    private int chargeStatus;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
