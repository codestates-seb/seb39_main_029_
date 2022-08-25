package codestates.preproject.stackoverflow.post.service;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.service.MemberService;
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
    private final MemberService memberService;
    public PostService(PostRepository postRepository, MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    public Posts createPost(Posts posts) {

        verifyPosts(posts);
        return postRepository.save(posts);
    }

    public Posts updatePost(Posts posts) {
        Posts post = findVerifiedPosts(posts.getPostId());

        Optional.ofNullable(posts.getSubject())
                .ifPresent(subject -> post.setSubject(subject));
        Optional.ofNullable(posts.getContent())
                .ifPresent(content -> post.setContent(content));
        Optional.ofNullable(posts.getTag())
                .ifPresent(tag -> post.setTag(tag));
        return postRepository.save(post);
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


    @Transactional(readOnly = true)
    public Posts findVerifiedPosts(long postId) {
        Optional<Posts> post = postRepository.findById(postId);
        Posts findPosts = post.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));


        return findPosts;
    }
    public void verifyPosts(Posts posts) {
        memberService.findVerifiedMember(posts.getMember().getMemberid());

        Optional<Posts> post = postRepository.findById(posts.getPostId());
        if (post.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.POST_EXISTS);
        }
    }
}

