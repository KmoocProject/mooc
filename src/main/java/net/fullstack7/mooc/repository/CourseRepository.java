package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.search.CourseSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, CourseSearch {
    List<Course> findByTeacherOrderByCreatedAtDesc(Teacher teacher);
    Optional<Course> findByCourseIdAndTeacher(int courseId, Teacher teacher);

    @Query("SELECT DISTINCT c FROM Course c " +
           "LEFT JOIN FETCH c.lectures l " +
           "LEFT JOIN FETCH l.contents lc " +
           "LEFT JOIN FETCH lc.lectureFile lf " +
           "LEFT JOIN FETCH lc.quizzes q " +
           "WHERE c.courseId = :courseId " +
           "ORDER BY l.lectureId ASC, lc.lectureContentId ASC")
    Optional<Course> findCourseWithContentsById(@Param("courseId") int courseId);
    Optional<Course> findByCourseId(int courseId);

    @Modifying
    @Query("update Course C set C.status = :status where C.courseId = :courseId")
    int updateStatus(int courseId, String status);


}
