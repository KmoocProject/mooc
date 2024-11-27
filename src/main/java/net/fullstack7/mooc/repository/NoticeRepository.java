package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Notice;
import net.fullstack7.mooc.search.NoticeSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Integer>, NoticeSearch {
    Optional<Notice> findByNoticeId(Integer noticeId);
    boolean existsNoticeByNoticeId(int noticeId);
    @Modifying
    @Query("update Notice N set N.title = :title, N.content = :content where N.noticeId = :noticeId")
    int updateNotice(int noticeId, String title, String content);
}
