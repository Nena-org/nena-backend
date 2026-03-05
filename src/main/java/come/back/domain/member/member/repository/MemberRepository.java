package come.back.domain.member.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import come.back.domain.member.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByTossUserKey(String tossUserKey);
}
