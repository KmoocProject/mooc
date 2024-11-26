package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Admin;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.domain.Teacher;
import net.fullstack7.mooc.dto.AdminLoginDTO;
import net.fullstack7.mooc.dto.AdminSearchDTO;
import net.fullstack7.mooc.repository.AdminRepository;
import net.fullstack7.mooc.repository.MemberRepository2;
import net.fullstack7.mooc.repository.TeacherRepository2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminServiceIf {
    private final AdminRepository adminRepository;
    private final TeacherRepository2 teacherRepository;
    private final TeacherRepository2 teacherRepository2;
    private final MemberRepository2 memberRepository2;

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        Optional<Admin> byAdminIdAndPassword = Optional.ofNullable(adminRepository.findByAdminIdAndPassword(adminLoginDTO.getAdminId(), adminLoginDTO.getPassword()));

        if (byAdminIdAndPassword.isPresent()) {
            return adminLoginDTO.getAdminId();
        }

        return null;
    }

    @Override
    public Page<Teacher> getTeachers(AdminSearchDTO adminSearchDTO) {
        String status = adminSearchDTO.getStatus();
        String searchId = adminSearchDTO.getSearchId();
        int isApproved = adminSearchDTO.getIsApproved();

        if (status != null && !status.equals("ACTIVE") && !status.equals("INACTIVE") && !status.equals("WITHDRAWN")) {
            status = null;
        }

        if (searchId != null && searchId.isEmpty()) {
            searchId = null;
        }

        if (isApproved < -1 || isApproved > 1) {
            isApproved = -1;
        }

        return teacherRepository2.adminTeacherPage(adminSearchDTO.getPageable(), isApproved, status, searchId);
    }

    @Override
    public Page<Member> getMembers(AdminSearchDTO adminSearchDTO) {
        String status = adminSearchDTO.getStatus();
        String searchId = adminSearchDTO.getSearchId();
        int memberType = adminSearchDTO.getMemberType();

        if (status != null && !status.equals("ACTIVE") && !status.equals("INACTIVE") && !status.equals("WITHDRAWN")) {
            status = null;
        }

        if (searchId != null && searchId.isEmpty()) {
            searchId = null;
        }

        if (memberType != 1) {
            memberType = -1;
        }

        return memberRepository2.adminMemberPage(adminSearchDTO.getPageable(), memberType, status, searchId);
    }
}
