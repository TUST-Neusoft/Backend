package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargeRepository extends JpaRepository<Charge, Integer> {
    List<Charge> findByUserId(int userId);
}
