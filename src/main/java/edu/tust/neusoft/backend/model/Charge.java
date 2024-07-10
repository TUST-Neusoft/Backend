package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "charge")
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "charge_no")
    private String chargeNo;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "charge_name")
    private String chargeName;

    @Column(name = "charge_status")
    private int chargeStatus;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
