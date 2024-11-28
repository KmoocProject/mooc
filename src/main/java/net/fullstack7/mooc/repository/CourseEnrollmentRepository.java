package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {
    int countAllByMember_MemberIdAndIsCompleted(String memberId, int isCompleted);
}
