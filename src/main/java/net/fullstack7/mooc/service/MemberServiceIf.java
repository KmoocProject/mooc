package net.fullstack7.mooc.service;

import net.fullstack7.mooc.dto.MemberDTO;

public interface MemberServiceIf {
    int registMember(MemberDTO memberDTO);
    MemberDTO loginMember(String memberId, String password);
    boolean deleteMember(String memberId);
}
