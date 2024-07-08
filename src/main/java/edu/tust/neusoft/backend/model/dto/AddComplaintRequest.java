package edu.tust.neusoft.backend.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddComplaintRequest {
    @JsonProperty("complaint_content")
    private String complaintContent;
}
