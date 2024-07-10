package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findByUserId(Long userId);
}
