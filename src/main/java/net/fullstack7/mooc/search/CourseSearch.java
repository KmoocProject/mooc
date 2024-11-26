package net.fullstack7.mooc.search;

import net.fullstack7.mooc.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseSearch {
    Page<Course> coursePage(Pageable pageable, String searchType, String searchWord);
}
