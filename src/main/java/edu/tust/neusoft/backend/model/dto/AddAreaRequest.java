package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAreaRequest {
    @JsonProperty("area_no")
    private String areaNo;
    @JsonProperty("area_name")
    private String areaName;
}
