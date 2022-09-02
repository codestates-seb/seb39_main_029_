package codestates.preproject.stackoverflow.tags.Repository;

import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tags,Long> {
    @Query(value = "select * from tags where posts_id = :postId", nativeQuery = true)
    Optional<List<Tags>> findTagsAll(long postId);

    @Query(value = "select * from tags where name = :name", nativeQuery = true)
    Optional<Tags> findByName(String name);
}


