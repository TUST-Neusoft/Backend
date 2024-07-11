package edu.tust.neusoft.backend.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class StoreDTO {
    private Long id;
    private int areaId;

    @JsonProperty("store_no")
    private String storeNo;

    private String storeName;
    private String storeAddress;
    private double maxLongitude;
    private double maxLatitude;
    private double minLongitude;
    private double minLatitude;
    private String storeIntroduce;
    private String startTime;
    private String closeTime;
    private int storeStatus;
    private Date createTime;
    private Date updateTime;
    private double longitude;
    private double latitude;

    // Getters and setters
}

