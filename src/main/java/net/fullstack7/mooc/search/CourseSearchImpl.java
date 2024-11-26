package net.fullstack7.mooc.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.QCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CourseSearchImpl extends QuerydslRepositorySupport implements CourseSearch {
    public CourseSearchImpl() {
        super(Course.class);
    }

    @Override
    public Page<Course> coursePage(Pageable pageable, String searchType, String searchWord) {

        return null;
    }
}
