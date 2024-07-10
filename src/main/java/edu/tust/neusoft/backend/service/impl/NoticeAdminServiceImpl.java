package edu.tust.neusoft.backend.service.impl;

import cn.hutool.core.date.DateTime;
import edu.tust.neusoft.backend.model.Notice;
import edu.tust.neusoft.backend.model.dto.NoticeRequest;
import edu.tust.neusoft.backend.repository.NoticeRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.NoticeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeAdminServiceImpl implements NoticeAdminService {
    private final NoticeRepository noticeRepository;

    @Autowired
    public NoticeAdminServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Result getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return Result.success("获取成功", notices);
    }

    @Override
    public Result addNotice(NoticeRequest noticeRequest) {
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeRequest.getNoticeTitle());
        notice.setNoticeContent(noticeRequest.getNoticeContent());
        notice.setNoticeStatus(noticeRequest.getNoticeStatus());
        noticeRepository.save(notice);
        return Result.success("添加成功", notice);
    }

    @Override
    public Result closeNoticeStatus(String noticeTitle) {
        Optional<Notice> optionalNotice = noticeRepository.findByNoticeTitle(noticeTitle);
        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            notice.setNoticeStatus(0); // 修改通知状态为0
            notice.setUpdateTime(DateTime.now());
            noticeRepository.save(notice);
            return Result.success("状态已更新", notice);
        } else {
            return Result.fail("未找到指定的通知");
        }
    }

    @Override
    public Result openNoticeStatus(String noticeTitle) {
        Optional<Notice> optionalNotice = noticeRepository.findByNoticeTitle(noticeTitle);
        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            notice.setNoticeStatus(1); // 修改通知状态为1
            notice.setUpdateTime(DateTime.now());
            noticeRepository.save(notice);
            return Result.success("状态已更新", notice);
        } else {
            return Result.fail("未找到指定的通知");
        }
    }
}
