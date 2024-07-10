package edu.tust.neusoft.backend.repository;

import edu.tust.neusoft.backend.model.NoticeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRecordRepository extends JpaRepository<NoticeRecord, Integer> {
    List<NoticeRecord> findByNoticeId(int noticeId);
}
