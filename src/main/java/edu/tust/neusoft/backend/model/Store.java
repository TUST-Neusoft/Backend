package edu.tust.neusoft.backend.model;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area_id")
    private int area_id;

    @Column(name = "store_no")
    private String storeNo;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_address")
    private String storeAddress;

    @Column(name = "max_longitude")
    private double maxLongitude;

    @Column(name = "max_latitude")
    private double maxLatitude;

    @Column(name = "min_longitude")
    private double minLongitude;

    @Column(name = "min_latitude")
    private double minLatitude;

    @Column(name = "longitude")
    private double Longitude;

    @Column(name = "latitude")
    private double Latitude;

    @Column(name = "store_introduce")
    private String storeIntroduce;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "close_time")
    private String closeTime;

    @Column(name = "store_status")
    private int storeStatus;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public int getAreaId() {
        return area_id;
    }
}

