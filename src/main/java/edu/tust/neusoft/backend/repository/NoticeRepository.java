package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
