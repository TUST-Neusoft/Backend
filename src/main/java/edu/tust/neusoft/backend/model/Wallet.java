package edu.tust.neusoft.backend.model;

import javax.persistence.*;
import java.util.Date;

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "wallet_balance")
    private double walletBalance;

    @Column(name = "wallet_password")
    private String walletPassword;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}
