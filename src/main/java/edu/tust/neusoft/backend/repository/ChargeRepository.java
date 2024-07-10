package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
    List<Charge> findByUserId(Long userId);
}
