package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.ChargeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargeDetailRepository extends JpaRepository<ChargeDetail, Long> {
    List<ChargeDetail> findByChargeNo(String chargeNo);
}
