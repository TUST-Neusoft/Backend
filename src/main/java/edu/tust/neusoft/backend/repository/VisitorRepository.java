package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.VisitorAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorAdmin, Long> {
    List<VisitorAdmin> findByUserId(Long userId);
    Optional<VisitorAdmin> findById(Long id);
}
