package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.ParkingBind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingBindRepository extends JpaRepository<ParkingBind, Long> {
    List<ParkingBind> findByParkingId(Long parkingId);
}
