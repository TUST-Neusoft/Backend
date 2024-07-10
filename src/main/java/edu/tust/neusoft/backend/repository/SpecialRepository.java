package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Special;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialRepository extends JpaRepository<Special, Long> {
    Optional<Special> findBySpecialName(String specialName);
}
