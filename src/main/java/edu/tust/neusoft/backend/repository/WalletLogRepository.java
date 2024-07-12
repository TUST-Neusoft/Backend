package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.WalletLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletLogRepository extends JpaRepository<WalletLog, Integer> {
    List<WalletLog> findByWalletId(int walletId);
}
