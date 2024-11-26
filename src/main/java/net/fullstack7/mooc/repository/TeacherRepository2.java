package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.search.TeacherSearch;
import net.fullstack7.mooc.search.TeacherSearchImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository2 extends JpaRepository<Teacher, String>, TeacherSearch {

}
