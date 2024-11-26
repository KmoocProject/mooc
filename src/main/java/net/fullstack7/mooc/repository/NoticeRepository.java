package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Notice;
import net.fullstack7.mooc.search.NoticeSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Integer>, NoticeSearch {
    Optional<Notice> findByNoticeId(Integer noticeId);
}
