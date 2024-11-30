package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.LearningHistory;
import net.fullstack7.mooc.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningHistoryRepository extends JpaRepository<LearningHistory, Integer> {
    LearningHistory findByLectureContentIdAndMember(int lectureContentId, Member member);
}
