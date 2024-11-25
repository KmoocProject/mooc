package net.fullstack7.mooc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.fullstack7.mooc.domain.Teacher;

import java.util.Optional;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    boolean existsByEmail(String email);
    boolean existsByTeacherId(String teacherId);
    Optional<Teacher> findByTeacherIdAndPassword(String teacherId, String password);
}
