package codestates.preproject.stackoverflow.pvote.repository;

import codestates.preproject.stackoverflow.pvote.entity.Pvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PVoteRepository extends JpaRepository<Pvote, Long> {

    @Query(value = "SELECT * FROM Pvote WHERE POSTS_ID = :postId AND MEMBER_ID = :memberId",nativeQuery = true)
    Optional<Pvote> findByPostIdMemberId(long postId, long memberId);
}
