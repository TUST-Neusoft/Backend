package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.AdminLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginLogRepository extends JpaRepository<AdminLoginLog, Integer> {
}
