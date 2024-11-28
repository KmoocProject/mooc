package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.dto.MemberModifyDTO;

public interface MemberServiceIf {
    public MemberDTO login(String memberId, String password);
    public String findId(String email);
    public MemberDTO viewMember(String memberId);
    public boolean pwdCheck(String memberId, String password);
    public int registMember(MemberDTO memberDTO);
    public int modifyMember(MemberDTO memberDTO);
    public boolean memberIdCheck(String memberId);
    public boolean emailCheck(String email);

    int modifyMember(MemberModifyDTO memberModifyDTO);

    int modifyWithoutPassword(MemberModifyDTO memberModifyDTO);

    void deleteMember(String memberId);
}
