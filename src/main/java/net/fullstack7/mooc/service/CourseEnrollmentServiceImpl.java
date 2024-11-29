package net.fullstack7.mooc.service;

import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.CourseEnrollment;
import net.fullstack7.mooc.dto.CourseEnrollmentDTO;
import net.fullstack7.mooc.repository.CourseEnrollmentRepository;
import net.fullstack7.mooc.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentServiceIf {
    CourseEnrollmentRepository courseEnrollmentRepository;
    CourseRepository courseRepository;

    @Override
    public int regist(CourseEnrollmentDTO courseEnrollmentDTO) {
        Course course = courseRepository.findById(courseEnrollmentDTO.getCourse().getCourseId()).orElseThrow(() -> new IllegalArgumentException("강의가 없음"));
        if(course.getIsCreditBank()==1){
            if(courseEnrollmentDTO.getMember().getCredit()==0){
                throw new IllegalArgumentException("학점은행제 회원이 아님");
            }
        }
        return courseEnrollmentRepository.save(CourseEnrollment.builder().course(course).member(courseEnrollmentDTO.getMember()).build()).getCourseEnrollmentId();
    }
}
