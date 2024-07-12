package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.VisitorRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitorRecords")
public class VisitorRecordsController {
    private final VisitorRecordsService visitorRecordsService;

    @Autowired
    public VisitorRecordsController(VisitorRecordsService visitorRecordsService) {
        this.visitorRecordsService = visitorRecordsService;
    }

    @GetMapping("/getAll")
    public Result getAllVisitorRecords(@CookieValue Long userId) {
        return visitorRecordsService.getAllVisitorRecordsByUserId(userId);
    }

    @PostMapping("/changeStatus")
    public Result changeVisitorStatus(@RequestParam Long id) {
        return visitorRecordsService.changeVisitorStatus(id);
    }
}
