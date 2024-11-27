package net.fullstack7.mooc.service;

import net.fullstack7.mooc.dto.MemberDTO;

public interface MemberServiceIf {
    public MemberDTO login(String memberId, String password);
    public MemberDTO viewMember(String memberId);
    public boolean pwdCheck(String memberId, String password);
    public int registMember(MemberDTO memberDTO);
    public int modifyMember(MemberDTO memberDTO);
    public int modifyWithoutPassword(MemberDTO memberDTO);
    public boolean memberIdCheck(String memberId);
    public boolean emailCheck(String email);
    void deleteMember(String memberId);
}
