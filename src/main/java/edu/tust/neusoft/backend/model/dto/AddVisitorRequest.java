package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddVisitorRequest {
    @JsonProperty("visitor_objective")
    private String visitorObjective;

    @JsonProperty("visitor_time")
    private String visitorTime;
}
