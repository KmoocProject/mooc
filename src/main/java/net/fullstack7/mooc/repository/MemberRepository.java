package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.search.MemberSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, MemberSearch {
    Optional<Member> findByMemberId(String memberId);
}
