package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Member;
import net.fullstack7.mooc.search.MemberSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository2 extends JpaRepository<Member, Integer>, MemberSearch {
}
