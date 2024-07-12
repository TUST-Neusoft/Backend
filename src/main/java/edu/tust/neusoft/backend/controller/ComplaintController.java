package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.AddComplaintRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
    private final ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/getMyComplaint")
    public Result getMyComplaint(@CookieValue int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return complaintService.getMyComplaint(userId);
    }

    @PostMapping("/addComplaint")
    public Result addComplaint(@CookieValue int userId, @RequestBody AddComplaintRequest request) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return complaintService.addComplaint(userId, request);
    }
}
