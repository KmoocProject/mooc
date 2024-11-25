package net.fullstack7.mooc.mapper;

import net.fullstack7.mooc.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    Member selectMemberById(String memberId);
    int registMember(Member member);
    int deleteMember(String memberId);
}
