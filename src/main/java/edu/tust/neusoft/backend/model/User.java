package edu.tust.neusoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "phone", length = 20, unique = true)
    private String phone;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "user_password", length = 100)
    private String userPassword;

    @Column(name = "sex", length = 2)
    private String sex;

    @Column(name = "mail", length = 50)
    private String mail;

    @Column(name = "avatar", length = 200)
    private String avatar;

    @Column(name = "last_login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    @Column(name = "user_status")
    private int userStatus;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
}