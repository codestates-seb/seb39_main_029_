package codestates.preproject.stackoverflow.cvote.repository;

import codestates.preproject.stackoverflow.cvote.entity.Cvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CvoteRepository extends JpaRepository<Cvote, Long> {
    @Query(value = "select * from cvote where meberid = :memberid and commentsid = :commentsid", nativeQuery = true)
    Optional<Cvote> findByMemberid(long memberid, long commentsid);
}
