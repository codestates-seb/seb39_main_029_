package codestates.preproject.stackoverflow.comments.repository;

import codestates.preproject.stackoverflow.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
