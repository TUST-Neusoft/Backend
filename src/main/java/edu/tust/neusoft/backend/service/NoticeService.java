package edu.tust.neusoft.backend.service;

import edu.tust.neusoft.backend.response.Result;

public interface NoticeService {
    Result getAllNotices();
    Result getNoticeRecords(int noticeId);
}
