package net.fullstack7.mooc.service;

import net.fullstack7.mooc.domain.Course;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.domain.Notice;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.AdminLoginDTO;
import net.fullstack7.mooc.dto.AdminSearchDTO;
import net.fullstack7.mooc.dto.NoticeDTO;
import net.fullstack7.mooc.dto.PageDTO;
import org.springframework.data.domain.Page;

public interface AdminServiceIf {
    String login(AdminLoginDTO adminLoginDTO);
    Page<Teacher> getTeachers(AdminSearchDTO adminSearchDTO);
    Page<Member> getMembers(AdminSearchDTO adminSearchDTO);

    Teacher getTeacher(String id);
    Member getMember(String id);

    String deleteMember(String id, String typeSelect);

    Page<Course> getCourses(AdminSearchDTO adminSearchDTO);
    Page<NoticeDTO> getNotices(PageDTO<Notice> pageDTO);
}
