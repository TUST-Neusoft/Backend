package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repair")
public class RepairController {
    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping("/getAll")
    public Result getAllComplaints(@CookieValue("user_id") Long userId) {
        return repairService.getAllComplaintsByUserId(userId);
    }

    @PostMapping("/changeStatus")
    public Result changeComplaintStatus(@RequestParam("id") Long id) {
        return repairService.changeComplaintStatus(id);
    }
}
