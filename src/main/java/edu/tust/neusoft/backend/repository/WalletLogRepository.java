package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.WalletLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletLogRepository extends JpaRepository<WalletLog, Integer> {
}
