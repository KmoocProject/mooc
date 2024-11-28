package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.search.MemberSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberSearch {
    @Query("SELECT m FROM Member m WHERE m.email = :email")
    Optional<Member> findByEmail(String email);

    @Modifying
    @Query("update Member M set M.status = :status where M.memberId = :memberId")
    int updateStatusByMemberId(String memberId, String status);

    @Modifying
    @Query("update Member M set M.memberType = 1 where M.memberId = :memberId")
    int updateMemberTypeById(String memberId);
}
