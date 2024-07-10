package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    List<Parking> findByUserId(Long userId);
}
