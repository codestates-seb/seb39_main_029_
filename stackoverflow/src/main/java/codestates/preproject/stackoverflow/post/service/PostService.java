package codestates.preproject.stackoverflow.post.service;

import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.service.MemberService;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.repository.PostRepository;
import codestates.preproject.stackoverflow.tags.entity.Tags;
import codestates.preproject.stackoverflow.tags.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final TagService tagService;
    private final PostRepository postRepository;
    private final MemberService memberService;
    public PostService(TagService tagService, PostRepository postRepository, MemberService memberService) {
        this.tagService = tagService;
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
                .ifPresent(tag -> {
                    List<Tags> ReTags = tagService.tagsFind(post.getPostId());
                    for (int i = 0; i < ReTags.size(); i++) {
                        ReTags.get(i).setData(tag.get(i).getData());
                    }
                    post.setTag(ReTags);
                });


        return postRepository.save(post);
    }

    public Posts findPost(long postId){
        Posts posts = findVerifiedPosts(postId);
        return posts;
    }

    public Page<Posts> findPosts(int page, int size,String arrange){
        return postRepository.findAll(PageRequest.of(page, size,
                Sort.by(arrange).descending()));
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

