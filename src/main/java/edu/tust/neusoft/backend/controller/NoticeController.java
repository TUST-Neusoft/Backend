package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/getAllNotice")
    public Result getAllNotices() {
        return noticeService.getAllNotices();
    }

    @GetMapping("/read")
    public Result getNoticeRecords(@RequestParam("notice_id") int noticeId) {
        return noticeService.getNoticeRecords(noticeId);
    }
}
