package codestates.preproject.stackoverflow.post.repository;

import codestates.preproject.stackoverflow.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
