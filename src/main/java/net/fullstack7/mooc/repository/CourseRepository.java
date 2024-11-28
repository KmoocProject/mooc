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

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>, CourseSearch {
    List<Course> findByTeacherOrderByCreatedAtDesc(Teacher teacher);
    Optional<Course> findByCourseIdAndTeacher(int courseId, Teacher teacher);


    @Modifying
    @Query("update Course C set C.status = :status where C.courseId = :courseId")
    int updateStatus(int courseId, String status);
}
