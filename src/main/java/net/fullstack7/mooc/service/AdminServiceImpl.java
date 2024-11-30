package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.*;
import net.fullstack7.mooc.dto.*;
import net.fullstack7.mooc.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminServiceIf {
    private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;
    private final CourseRepository courseRepository;

    public boolean existsAdmin(String id) {
        return adminRepository.existsById(id);
    }

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


        Page<Teacher> teachers = teacherRepository.adminTeacherPage(adminSearchDTO.getPageable(), isApproved, status, searchId);

        adminSearchDTO.setTotalCount((int)teachers.getTotalElements());
        teachers = teacherRepository.adminTeacherPage(adminSearchDTO.getPageable(), isApproved, status, searchId);

        return teachers;
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

        Page<Member> members = memberRepository.adminMemberPage(adminSearchDTO.getPageable(), memberType, status, searchId);

        adminSearchDTO.setTotalCount((int)members.getTotalElements());
        members = memberRepository.adminMemberPage(adminSearchDTO.getPageable(), memberType, status, searchId);

        return members;
    }

    @Override
    public String modifyMemberStatus(String type, String userId) {
        String status = type.substring(0, type.length() -1);
        if(type.endsWith("t") && teacherRepository.existsByTeacherId(userId) ) {
            teacherRepository.updateStatusByTeacherId(userId, status);
        }
        else if(type.endsWith("s") && memberRepository.existsById(userId)) {
            memberRepository.updateStatusByMemberId(userId, status);
        }
        else {
            return "변경 불가";
        }
        return "변경되었습니다.";
    }

    @Override
    public String approveTeacherRegist(String teacherId) {
        if(teacherRepository.existsByTeacherId(teacherId)){
            teacherRepository.updateIsApprovedByTeacherId(teacherId, 1);
            teacherRepository.updateStatusByTeacherId(teacherId, "ACTIVE");
            return teacherId + " 승인 완료";
        }
        return "존재하지 않는 계정입니다.";
    }

    @Override
    public Teacher getTeacher(String id) {
        Optional<Teacher> teacher = teacherRepository.findByTeacherId(id);
        return teacher.orElse(null);
    }

    @Override
    public Member getMember(String id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    @Override
    public String deleteMember(String id, String typeSelect) {

        if(typeSelect.equals("s")) {
            if(getMember(id) == null)
                return "존재하지 않는 회원입니다.";

            memberRepository.updateStatusByMemberId(id, "WITHDRAWN");
        }

        if(typeSelect.equals("t")) {
            if(getTeacher(id) == null)
                return "존재하지 않는 회원입니다.";

            teacherRepository.updateStatusByTeacherId(id, "WITHDRAWN");
        }

        return "탈퇴 완료";
    }

    @Override
    public Page<CourseResponseDTO> getCourses(CourseSearchDTO searchDTO) {
        Page<CourseResponseDTO> courses = courseRepository.coursePage(searchDTO.getPageable(), searchDTO, null, -1);
        searchDTO.setTotalCount((int)courses.getTotalElements());
        courses = courseRepository.coursePage(searchDTO.getPageable(), searchDTO, null, -1);
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public String modifyCourseStatus(String type, int courseId) {
        if(!type.equals("DRAFT") && !type.equals("PUBLISHED") && !type.equals("DELETED")) {
            return "타입 오류";
        }

        if(courseRepository.existsById(courseId)) {
            courseRepository.updateStatus(courseId, type);
            return "변경 완료";
        }
        return "없는 강의";
    }

    @Override
    public String insertNotice(NoticeDTO dto) {
        Notice notice = Notice.builder()
                .admin(Admin.builder().adminId(dto.getAdminId()).build())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .importance(dto.getImportance())
                .build();
        noticeRepository.save(notice);

        if(notice.getNoticeId() != 0) {
            return "등록 완료";
        }

        return null;
    }

    @Override
    public String modifyNotice(NoticeDTO dto) {
        if(noticeRepository.findById(dto.getNoticeId()) == null) {
            return "존재하지 않는 게시글입니다.";
        }
        if(!adminRepository.existsById(dto.getAdminId())) {
            return "관리자 권한 계정만 수정 가능합니다.";
        }
        noticeRepository.updateNotice(dto.getNoticeId(), dto.getTitle(), dto.getContent(), dto.getImportance());
        return "수정 완료";
    }

    @Override
    public String deleteNotice(int noticeId, String adminId) {
        if(!adminRepository.existsById(adminId))
            return "관리자 권한 계정만 삭제 가능합니다.";
        if(noticeRepository.existsById(noticeId)) {
            noticeRepository.delete(noticeRepository.findById(noticeId).get());
            return "삭제 완료";
        }
        return "존재하지 않는 게시글입니다.";
    }

    @Override
    public Map<String, Integer> mainPageInfo() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minus1month = now.toLocalDate().atStartOfDay().minusMonths(1);
        int newMemberOneMonth = memberRepository.countByCreatedAtIsBetween(minus1month, now)
                + teacherRepository.countByCreatedAtIsBetween(minus1month, now);
        int totalMemberCount = memberRepository.countByStatusIn(new ArrayList<>(List.of(new String[]{"ACTIVE", "INACTIVE"})))
                + teacherRepository.countByStatusIn(new ArrayList<>(List.of(new String[]{"ACTIVE", "INACTIVE"})));

        int newCourseOneMonth = courseRepository.countByCreatedAtIsBetween(minus1month, now);
        int totalCourseCount = courseRepository.countByStatusIn(new ArrayList<>(List.of(new String[]{"PUBLISHED"})));

        Map<String, Integer> map = new HashMap<>();
        map.put("newMemberOneMonth", newMemberOneMonth);
        map.put("totalMemberCount", totalMemberCount);
        map.put("totalCourseCount", totalCourseCount);
        map.put("newCourseOneMonth", newCourseOneMonth);

        return map;
    }


}
