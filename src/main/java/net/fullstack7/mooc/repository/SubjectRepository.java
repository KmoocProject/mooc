package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAllByOrderBySubjectIdAsc();
} 
