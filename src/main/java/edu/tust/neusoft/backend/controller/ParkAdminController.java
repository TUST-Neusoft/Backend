package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ParkAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parkAdmin")
public class ParkAdminController {
    private final ParkAdminService parkAdminService;

    @Autowired
    public ParkAdminController(ParkAdminService parkAdminService) {
        this.parkAdminService = parkAdminService;
    }

    @GetMapping("/getAll")
    public Result getAllParking(@CookieValue("user_id") Long userId) {
        return parkAdminService.getAllParkingByUserId(userId);
    }
}
