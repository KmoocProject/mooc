package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import net.fullstack7.mooc.dto.CourseResponseDTO;
import net.fullstack7.mooc.dto.CourseSearchDTO;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.dto.MemberModifyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface MemberServiceIf {
    public MemberDTO login(String memberId, String password);
    public String findId(MemberDTO memberDTO);
    public String findPwd(MemberDTO memberDTO);
    public MemberDTO viewMember(String memberId);
    public boolean pwdCheck(String memberId, String password);
    public int registMember(MemberDTO memberDTO);
    public int modifyMember(MemberDTO memberDTO);
    public boolean memberIdCheck(String memberId);
    public boolean emailCheck(String email);

    public boolean isValidPassword(String password);
    public boolean isValidEmail(String email);
    String validateMember(MemberModifyDTO memberDTO, Model model);

    int modifyMember(MemberModifyDTO memberModifyDTO);
    int modifyWithoutPassword(MemberModifyDTO memberModifyDTO);
    void deleteMember(String memberId);

    Page<CourseResponseDTO> getCourses(CourseSearchDTO searchDTO, String memberId, int isCompleted);
    int getMyCourseCount(String memberId, int isCompleted);

    String modifyToCredit(String memberId);
}
