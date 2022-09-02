package codestates.preproject.stackoverflow.pvote.repository;

import codestates.preproject.stackoverflow.pvote.entity.Pvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PVoteRepository extends JpaRepository<Pvote, Long> {

    @Query(value = "select * from pvote where post_id = :postId and member_id = :memberId",nativeQuery = true)
    Optional<Pvote> findByPostIdMemberId(long postId, long memberId);
}
