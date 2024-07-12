package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.AddVisitorRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitor")
public class VisitorController {
    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping("/getMyVisitor")
    public Result getMyVisitor(@CookieValue int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return visitorService.getMyVisitor((long)userId);
    }

    @PostMapping("/addVisitor")
    public Result addVisitor(@CookieValue int userId, @RequestBody AddVisitorRequest request) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return visitorService.addVisitor((long)userId, request);
    }
}
