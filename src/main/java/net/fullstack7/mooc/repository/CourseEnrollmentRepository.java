package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.CourseEnrollment;
import net.fullstack7.mooc.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {
    int countAllByMember_MemberIdAndIsCompleted(String memberId, int isCompleted);
    Optional<CourseEnrollment> findByCourseAndMember(Course course, Member member);
}
