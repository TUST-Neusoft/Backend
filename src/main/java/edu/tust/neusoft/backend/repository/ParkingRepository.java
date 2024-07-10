package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Integer> {
    List<Parking> findByUserId(int userId);
}
