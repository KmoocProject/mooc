package net.fullstack7.mooc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.dto.MemberDTO;
import net.fullstack7.mooc.mapper.MemberMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberServiceIf {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Override
    public MemberDTO login(String memberId, String password) {
        Member member = memberMapper.login(memberId);
        if(member != null && member.getPassword().equals(password)) {
            if(member.getStatus().equals("ACTIVE")&&member.getMemberType() == 0){
            return modelMapper.map(member, MemberDTO.class);
            }
        }
        return null;
    }

    @Override
    public MemberDTO viewMember(String memberId) {
        Member member = memberMapper.viewMember(memberId);
        if(member != null && member.getMemberType() == 0){
            return modelMapper.map(member, MemberDTO.class);
        }
        return null;
    }

    @Override
    public int registMember(MemberDTO memberDTO) {
//        log.info("memberDTO: {}", memberDTO);
        Member member = modelMapper.map(memberDTO, Member.class);
        return memberMapper.registMember(member);
    }

    @Override
    public boolean memberIdCheck(String memberId) {
        String result = memberMapper.memberIdCheck(memberId);
        return result == null;
//        return memberMapper.memberIdCheck(memberId) == null;
    }

    @Override
    public boolean emailCheck(String email) {
        String result = memberMapper.emailCheck(email);
        return result == null;
    }


    @Override
    public boolean pwdCheck(String memberId, String password) {
        String result = memberMapper.pwdCheck(memberId);
        return result != null && result.equals(password);
    }

    @Override
    public int modifyMember(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        return memberMapper.modifyMember(member);
    }


    @Override
    @Transactional
    public void deleteMember(String memberId) {
        memberMapper.deleteMember(memberId);
    }


}
