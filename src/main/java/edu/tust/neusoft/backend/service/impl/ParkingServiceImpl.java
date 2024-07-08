package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Parking;
import edu.tust.neusoft.backend.model.ParkingBind;
import edu.tust.neusoft.backend.repository.ParkingRepository;
import edu.tust.neusoft.backend.repository.ParkingBindRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingBindRepository parkingBindRepository;

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository, ParkingBindRepository parkingBindRepository) {
        this.parkingRepository = parkingRepository;
        this.parkingBindRepository = parkingBindRepository;
    }

    @Override
    public Result getMyParking(int userId) {
        List<Parking> parkings = parkingRepository.findByUserId(userId);
        return Result.success("获取成功", parkings);
    }

    @Override
    public Result bindParking(int parkingId, String licensePlate) {
        // 检查parking_id是否存在
        Parking parking = parkingRepository.findById(parkingId).orElse(null);
        if (parking == null) {
            return Result.fail("车位不存在");
        }

        ParkingBind parkingBind = new ParkingBind();
        parkingBind.setParkingId(parkingId);
        parkingBind.setLicensePlate(licensePlate);
        parkingBind.setCreateTime(new Date());
        parkingBind.setUpdateTime(new Date());
        parkingBindRepository.save(parkingBind);
        return Result.success("绑定成功", parkingBind);
    }
}
