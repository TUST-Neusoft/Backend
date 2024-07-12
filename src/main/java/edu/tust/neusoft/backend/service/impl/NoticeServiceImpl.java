package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Notice;
import edu.tust.neusoft.backend.model.NoticeRecord;
import edu.tust.neusoft.backend.repository.NoticeRecordRepository;
import edu.tust.neusoft.backend.repository.NoticeRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeRecordRepository noticeRecordRepository;

    @Autowired
    public NoticeServiceImpl(NoticeRepository noticeRepository, NoticeRecordRepository noticeRecordRepository) {
        this.noticeRepository = noticeRepository;
        this.noticeRecordRepository = noticeRecordRepository;
    }

    @Override
    public Result getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return Result.success("获取成功", notices);
    }

    @Override
    public Result getNoticeRecords(int noticeId) {
        List<NoticeRecord> noticeRecords = noticeRecordRepository.findByNoticeId(noticeId);
        return Result.success("获取成功", noticeRecords);
    }
}
