package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.ChargeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeRepository extends JpaRepository<ChargeAdmin, Long> {
    List<ChargeAdmin> findByUserId(Long userId);
}
