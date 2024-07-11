package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.NoticeAdmin;
import edu.tust.neusoft.backend.model.dto.NoticeRequest;
import edu.tust.neusoft.backend.repository.NoticeAdminRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.NoticeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeAdminServiceImpl implements NoticeAdminService {
    private final NoticeAdminRepository noticeAdminRepository;

    @Autowired
    public NoticeAdminServiceImpl(NoticeAdminRepository noticeAdminRepository) {
        this.noticeAdminRepository = noticeAdminRepository;
    }

    @Override
    public Result getAllNotices() {
        List<NoticeAdmin> noticeAdmins = noticeAdminRepository.findAll();
        return Result.success("获取成功", noticeAdmins);
    }

    @Override
    public Result addNotice(NoticeRequest noticeRequest) {
        NoticeAdmin noticeAdmin = new NoticeAdmin();
        noticeAdmin.setNoticeTitle(noticeRequest.getNoticeTitle());
        noticeAdmin.setNoticeContent(noticeRequest.getNoticeContent());
        noticeAdmin.setNoticeStatus(noticeRequest.getNoticeStatus());
        noticeAdmin.setCreateTime(LocalDateTime.now());
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdminRepository.save(noticeAdmin);
        return Result.success("添加成功", noticeAdmin);
    }

    @Override
    public Result closeNoticeStatus(String noticeTitle) {
        Optional<NoticeAdmin> optionalNotice = noticeAdminRepository.findByNoticeTitle(noticeTitle);
        if (optionalNotice.isPresent()) {
            NoticeAdmin noticeAdmin = optionalNotice.get();
            noticeAdmin.setNoticeStatus(0); // 修改通知状态为0
            noticeAdmin.setUpdateTime(LocalDateTime.now());
            noticeAdminRepository.save(noticeAdmin);
            return Result.success("状态已更新", noticeAdmin);
        } else {
            // 增加日志打印
            System.out.println("查询参数: " + noticeTitle);
            List<NoticeAdmin> allNotices = noticeAdminRepository.findAll();
            for (NoticeAdmin notice : allNotices) {
                System.out.println("数据库中的通知: " + notice.getNoticeTitle());
            }
            return Result.fail("未找到指定的通知");
        }
    }


    @Override
    public Result openNoticeStatus(String noticeTitle) {
        Optional<NoticeAdmin> optionalNotice = noticeAdminRepository.findByNoticeTitle(noticeTitle);
        if (optionalNotice.isPresent()) {
            NoticeAdmin noticeAdmin = optionalNotice.get();
            noticeAdmin.setNoticeStatus(1); // 修改通知状态为1
            noticeAdmin.setUpdateTime(LocalDateTime.now());
            noticeAdminRepository.save(noticeAdmin);
            return Result.success("状态已更新", noticeAdmin);
        } else {
            return Result.fail("未找到指定的通知");
        }
    }
}
