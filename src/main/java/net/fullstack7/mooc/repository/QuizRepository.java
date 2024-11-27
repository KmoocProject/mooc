package net.fullstack7.mooc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fullstack7.mooc.domain.Quiz;
import net.fullstack7.mooc.domain.LectureContent;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByLectureContentOrderByQuizIdAsc(LectureContent lectureContent);
    Optional<Quiz> findByQuizId(int quizId);
    Optional<Quiz> findByLectureContent(LectureContent lectureContent);
}
