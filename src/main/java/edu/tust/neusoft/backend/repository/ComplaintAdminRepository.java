package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.ComplaintAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintAdminRepository extends JpaRepository<ComplaintAdmin, Long> {
    List<ComplaintAdmin> findByUserId(Long userId);
}
