package edu.tust.neusoft.backend.model.dto;

import lombok.Data;

@Data
public class NoticeRequest {
    private String noticeTitle;
    private String noticeContent;
    private int noticeStatus;
}
