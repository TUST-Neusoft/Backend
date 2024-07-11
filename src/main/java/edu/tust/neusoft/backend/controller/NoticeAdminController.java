package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.NoticeAdminService;
import edu.tust.neusoft.backend.model.dto.NoticeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/noticeAdmin")
public class NoticeAdminController {
    private final NoticeAdminService noticeAdminService;

    @Autowired
    public NoticeAdminController(NoticeAdminService noticeAdminService) {
        this.noticeAdminService = noticeAdminService;
    }

    @GetMapping("/getAllnotice")
    public Result getAllNotices() {
        return noticeAdminService.getAllNotices();
    }

    @PostMapping("/addNotice")
    public Result addNotice(@RequestBody NoticeRequest noticeRequest) {
        return noticeAdminService.addNotice(noticeRequest);
    }

    @PostMapping("/closeStatus")
    public Result closeNoticeStatus(@RequestParam String noticeTitle) {
        return noticeAdminService.closeNoticeStatus(noticeTitle);
    }

    @PostMapping("/openStatus")
    public Result openNoticeStatus(@RequestParam String noticeTitle) {
        return noticeAdminService.openNoticeStatus(noticeTitle);
    }
}
