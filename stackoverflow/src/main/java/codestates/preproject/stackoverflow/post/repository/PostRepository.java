package codestates.preproject.stackoverflow.post.repository;

import codestates.preproject.stackoverflow.post.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {
    //상수가 추가한 코드입니다.
    @Query(value = "Select * from POSTS WHERE memberid = :memberid ORDER BY POST_ID DESC LIMIT :page, :size",nativeQuery = true)
    List<Posts> findByMemberid(long memberid, int page, int size);
//    @Query(value = "Select * from POSTS WHERE lower(CONTENT) = :word ORDER BY ")
//    List<Posts> findByWord(String word, int page, int size);
}
