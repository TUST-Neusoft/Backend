package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.ParkingAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingAdmin, Long> {
    List<ParkingAdmin> findByUserId(Long userId);
}
