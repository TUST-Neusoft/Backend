package edu.tust.neusoft.backend.controller;

import edu.tust.neusoft.backend.model.dto.ParkingBindRequest;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/getMyParking")
    public Result getMyParking(@CookieValue int userId) {
        if (userId == -1) {
            return Result.fail("用户未登录");
        }
        return parkingService.getMyParking(userId);
    }

    @PostMapping("/bind")
    public Result bindParking(@RequestBody ParkingBindRequest request) {
        return parkingService.bindParking(request.getParkingId(), request.getLicensePlate());
    }
}
