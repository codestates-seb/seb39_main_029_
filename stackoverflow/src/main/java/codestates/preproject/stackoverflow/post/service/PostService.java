package codestates.preproject.stackoverflow.post.service;

import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Posts createPost(Posts posts) {

        verifyPosts(posts);
        return postRepository.save(posts);
    }

    public Posts updatePost(Posts posts) {

        verifyPosts(posts);
        return postRepository.save(posts);
    }

    public Posts findPost(long postId){
        return findVerifiedPosts(postId);
    }

    public Page<Posts> findPosts(int page, int size){
        return postRepository.findAll(PageRequest.of(page, size,
                Sort.by("votes").descending()));
    }

    public void deletePost(long postId) {
        Posts post = findVerifiedPosts(postId);
        postRepository.delete(post);
    }


    private Posts findVerifiedPosts(long postId) {
        Optional<Posts> post = postRepository.findById(postId);
        Posts findPosts = post.orElseThrow(() ->
                new RuntimeException());

        //나중에 예외처리 다 바꾸기
        return findPosts;
    }
    private void verifyPosts(Posts posts) {
        Optional<Posts> findPosts = postRepository.findById(posts.getPostId());

    }
}

