package net.fullstack7.mooc.service;

import net.fullstack7.mooc.dto.CourseEnrollmentDTO;

public interface CourseEnrollmentServiceIf {
    public int regist(CourseEnrollmentDTO courseEnrollmentDTO);
    CourseEnrollmentDTO isEnrolled(CourseEnrollmentDTO courseEnrollmentDTO);
    public String delete(CourseEnrollmentDTO courseEnrollmentDTO);
}
