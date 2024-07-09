package edu.tust.neusoft.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "portal_wallet_log")
public class WalletLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "wallet_id", nullable = false)
    private int walletId;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "state", nullable = false)
    private int state;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
}
