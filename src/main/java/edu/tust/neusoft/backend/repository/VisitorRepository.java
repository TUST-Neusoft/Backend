package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByUserId(Long userId);
    Optional<Visitor> findById(Long id);
}
