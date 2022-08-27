package codestates.preproject.stackoverflow.comments.repository;

import codestates.preproject.stackoverflow.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query(value = "SELECT * FROM COMMENTS WHERE POSTSID = :postsid", nativeQuery = true)
    List<Comments> findByPostsId(long postsid);
}
