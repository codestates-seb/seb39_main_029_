package codestates.preproject.stackoverflow.member.repository;

import codestates.preproject.stackoverflow.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * FROM MEMBER WHERE NICK_NAME = :nickName", nativeQuery = true)
    Optional<Member> findByNickName(String nickName);

    @Query(value = "SELECT * FROM MEMBER WHERE email = :email", nativeQuery = true)
    Optional<Member> findByEmail(String email);
}
