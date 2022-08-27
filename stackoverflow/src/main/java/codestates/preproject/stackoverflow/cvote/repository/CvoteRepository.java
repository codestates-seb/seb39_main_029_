package codestates.preproject.stackoverflow.cvote.repository;

import codestates.preproject.stackoverflow.cvote.entity.Cvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CvoteRepository extends JpaRepository<Cvote, Long> {
    @Query(value = "select * from Cvote WHERE MEMBERID = :memberid AND COMMENTSID = :commentsid", nativeQuery = true)
    Optional<Cvote> findByMemberid(long memberid, long commentsid);
}
