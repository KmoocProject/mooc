package net.fullstack7.mooc.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import net.fullstack7.mooc.domain.*;
import net.fullstack7.mooc.dto.CourseResponseDTO;
import net.fullstack7.mooc.dto.CourseSearchDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseSearchImpl extends QuerydslRepositorySupport implements CourseSearch {
    public CourseSearchImpl() {
        super(Course.class);
    }
    @Override
    public Page<CourseResponseDTO> coursePage(Pageable pageable, CourseSearchDTO courseSearchDTO, String memberId) {

        QCourse qCourse = QCourse.course;

        JPQLQuery<Course> query = from(qCourse);

        BooleanBuilder builder = new BooleanBuilder();

        if(memberId != null && !memberId.isEmpty()) {
            QCourseEnrollment qCourseEnrollment = QCourseEnrollment.courseEnrollment;
            query.innerJoin(qCourseEnrollment).on(qCourseEnrollment.course.eq(qCourse));
            builder.and(qCourseEnrollment.member.memberId.eq(memberId));
        }


        //학점은행제
        if(courseSearchDTO.getIsCreditBank() > -1)
            builder.and(qCourse.isCreditBank.eq(courseSearchDTO.getIsCreditBank()));
        //상태
        if(courseSearchDTO.getStatus() != null && !courseSearchDTO.getStatus().isEmpty())
            builder.and(qCourse.status.eq(courseSearchDTO.getStatus()));
        //과목
        if(courseSearchDTO.getSubjectId() > -1)
            builder.and(qCourse.subject.subjectId.eq(courseSearchDTO.getSubjectId()));

        if(courseSearchDTO.getSearchField() != null && courseSearchDTO.getSearchValue() != null && !courseSearchDTO.getSearchValue().isEmpty()) {
            switch (courseSearchDTO.getSearchField()) {
                case "title":
                    builder.and(qCourse.title.contains(courseSearchDTO.getSearchValue()));
                    break;
            }
        }

        if(builder.hasValue())
            query.where(qCourse.title.contains(courseSearchDTO.getSearchValue()));

        if(courseSearchDTO.getSortField() != null && !courseSearchDTO.getSortField().isEmpty() && courseSearchDTO.getSortDirection() != null && !courseSearchDTO.getSortDirection().isEmpty()) {
            switch (courseSearchDTO.getSortField()) {
//                기본순, 최신등록순, 가나다순
                case "title":
                    query.orderBy(qCourse.title.asc());
                    break;
                case "createdAt":
                    query.orderBy(qCourse.createdAt.desc());
                    break;
            }
        }

        Objects.requireNonNull(this.getQuerydsl()).applyPagination(pageable, query);
        List<Course> courses = query.fetch();

        List<CourseResponseDTO> courses2 = courses.stream().map(entity -> CourseResponseDTO.builder()
                .courseId(entity.getCourseId())
                .isCreditBank(entity.getIsCreditBank())
                .title(entity.getTitle())
                .weeks(entity.getWeeks())
                .thumbnail(entity.getThumbnail())
                .learningTime(entity.getLearningTime())
                .description(entity.getDescription())
                .language(entity.getLanguage())
                .build()
        ).toList();

        int total = (int) query.fetchCount();

        return new PageImpl<>(courses2, pageable, total);
    }
}
