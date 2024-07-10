package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.PortalLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortalLoginLogRepository extends JpaRepository<PortalLoginLog, Integer> {
}
