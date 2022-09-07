package codestates.preproject.stackoverflow.post.repository;

import codestates.preproject.stackoverflow.post.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {
    //상수가 추가한 코드입니다.
    @Query(value = "Select * from posts where memberid = :memberid order by post_id desc limit :page, :size",nativeQuery = true)
    List<Posts> findByMemberid(long memberid, int page, int size);
    @Query(value = "Select * from posts where lower(subject) like :word order by created_at desc", nativeQuery = true)
    List<Posts> findByWord(String word);
//    Other 쿼리문
//    @Query(value = "Select * from POSTS WHERE LOWER(subject) LIKE :word ORDER BY CREATED_AT DESC", nativeQuery = true)
//    Page<Posts> findAllTest(Pageable pageable, String word);
}
