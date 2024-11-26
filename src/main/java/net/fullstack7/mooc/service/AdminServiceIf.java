package net.fullstack7.mooc.service;

import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.AdminLoginDTO;
import net.fullstack7.mooc.dto.AdminSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminServiceIf {
    String login(AdminLoginDTO adminLoginDTO);
    Page<Teacher> getTeachers(AdminSearchDTO adminSearchDTO);
    Page<Member> getMembers(AdminSearchDTO adminSearchDTO);

    Teacher getTeacher(String id);
    Member getMember(String id);
}
