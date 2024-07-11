package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.NoticeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeAdmin, Long> {
    Optional<NoticeAdmin> findByNoticeTitle(String noticeTitle);
}
