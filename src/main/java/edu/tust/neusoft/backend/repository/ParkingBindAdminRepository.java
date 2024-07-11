package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.ParkingBindAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingBindAdminRepository extends JpaRepository<ParkingBindAdmin, Long> {
    List<ParkingBindAdmin> findByParkingId(Long parkingId);
}
