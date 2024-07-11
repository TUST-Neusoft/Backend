package edu.tust.neusoft.backend.service.impl;

import edu.tust.neusoft.backend.model.ParkingAdmin;
import edu.tust.neusoft.backend.model.ParkingBindAdmin;
import edu.tust.neusoft.backend.model.dto.ParkingResponse;
import edu.tust.neusoft.backend.repository.ParkingBindRepository;
import edu.tust.neusoft.backend.repository.ParkingAdminRepository;
import edu.tust.neusoft.backend.response.Result;
import edu.tust.neusoft.backend.service.ParkAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkAdminServiceImpl implements ParkAdminService {
    private final ParkingAdminRepository parkingAdminRepository;
    private final ParkingBindRepository parkingBindRepository;

    @Autowired
    public ParkAdminServiceImpl(ParkingAdminRepository parkingAdminRepository, ParkingBindRepository parkingBindRepository) {
        this.parkingAdminRepository = parkingAdminRepository;
        this.parkingBindRepository = parkingBindRepository;
    }

    @Override
    public Result getAllParkingByUserId(Long userId) {
        List<ParkingAdmin> parkingAdmins = parkingAdminRepository.findByUserId(userId);
        List<ParkingResponse> responseList = parkingAdmins.stream().map(parkingAdmin -> {
            List<ParkingBindAdmin> parkingBindAdmins = parkingBindRepository.findByParkingId(parkingAdmin.getId());
            ParkingResponse response = new ParkingResponse();
            response.setId(parkingAdmin.getId());
            response.setUserId(parkingAdmin.getUserId());
            response.setParkingName(parkingAdmin.getParkingName());
            response.setParkingType(parkingAdmin.getParkingType());
            response.setParkingStatus(parkingAdmin.getParkingStatus());
            response.setCreateTime(parkingAdmin.getCreateTime());
            response.setUpdateTime(parkingAdmin.getUpdateTime());
            response.setParkingBindAdmins(parkingBindAdmins);
            return response;
        }).collect(Collectors.toList());

        return Result.success("获取成功", responseList);
    }
}
