package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.model.dto.NoticeRequest;

public interface NoticeAdminService {
    Result getAllNotices();
    Result addNotice(NoticeRequest noticeRequest);
    Result closeNoticeStatus(String noticeTitle);
    Result openNoticeStatus(String noticeTitle);
}
