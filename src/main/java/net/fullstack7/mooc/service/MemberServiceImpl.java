package net.fullstack7.mooc.service;

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

//    @Override
    public int getMember(String memberId){
        Member member = memberMapper.selectMemberById(memberId);
//        return modelMapper.map(member, MemberDTO.class);
        return 0;
    }

    @Override
    public int registMember(MemberDTO memberDTO) {
        try{
            Member member = modelMapper.map(memberDTO, Member.class);
//            return memberMapper.registMember(member) > 0;
        } catch (Exception e){
            log.error("회원가입실패",e);
//            return false;
        }
        return 0;
    }

    @Override
    public MemberDTO loginMember(String memberId, String password) {
        Member member = memberMapper.selectMemberById(memberId);
        if(member != null){
//            return member.getPassword().equals(password);
        }
//        return false;
        return null;
    }

    @Override
    public boolean deleteMember(String memberId) {
        return memberMapper.deleteMember(memberId) > 0;
    }
}
