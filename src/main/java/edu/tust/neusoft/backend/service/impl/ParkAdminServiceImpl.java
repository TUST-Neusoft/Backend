package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.Parking;
import edu.tust.neusoft.backend.model.ParkingBind;
import edu.tust.neusoft.backend.model.dto.ParkingResponse;
import edu.tust.neusoft.backend.repository.ParkingBindRepository;
import edu.tust.neusoft.backend.repository.ParkingRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ParkAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkAdminServiceImpl implements ParkAdminService {
    private final ParkingRepository parkingRepository;
    private final ParkingBindRepository parkingBindRepository;

    @Autowired
    public ParkAdminServiceImpl(ParkingRepository parkingRepository, ParkingBindRepository parkingBindRepository) {
        this.parkingRepository = parkingRepository;
        this.parkingBindRepository = parkingBindRepository;
    }

    @Override
    public Result getAllParking() {
        List<Parking> parkings = parkingRepository.findAll();
        List<ParkingResponse> responseList = parkings.stream().map(parking -> {
            List<ParkingBind> parkingBinds = parkingBindRepository.findByParkingId(parking.getId());
            ParkingResponse response = new ParkingResponse();
            response.setId(parking.getId());
            response.setUserId(parking.getUserId());
            response.setParkingName(parking.getParkingName());
            response.setParkingType(parking.getParkingType());
            response.setParkingStatus(parking.getParkingStatus());
            response.setCreateTime(parking.getCreateTime());
            response.setUpdateTime(parking.getUpdateTime());
            response.setParkingBinds(parkingBinds);
            return response;
        }).collect(Collectors.toList());

        return Result.success("获取成功", responseList);
    }
}
