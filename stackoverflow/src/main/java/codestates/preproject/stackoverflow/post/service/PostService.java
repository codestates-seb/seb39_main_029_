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
        Posts post = findTagsId(posts);
        return postRepository.save(post);
    }

    public Posts updatePost(Posts posts) {
        Posts post = findVerifiedPosts(posts.getPostId());

        Optional.ofNullable(posts.getSubject())
                .ifPresent(subject -> post.setSubject(subject));
        Optional.ofNullable(posts.getContent())
                .ifPresent(content -> post.setContent(content));
        Optional.ofNullable(posts.getPostTagsList())
                .ifPresent(tag -> {
                    for (int i = 0; i < tag.size(); i++) {
                        post.getPostTagsList().get(i).getTags().setName(
                                tagService.findVerifiedTags(tag.get(i).getTags().getTagsId()).getName()
                        );
                    }
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

    //태그 클릭 페이지에서 question 페이지 이동시 사용하는 service 메서드
    public List<Posts> tagsCheck(List<Posts> posts, int tagCheckId) {
        List<Posts> result = new ArrayList<>();

        Tags tag = tagService.findVerifiedTags(tagCheckId);
        for (int i = 0; i < posts.size(); i++) {
            if (tag.getPostTags().contains(posts.get(i))) {
                result.add(posts.get(i));
            }
        }

        return result;
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

    public Posts findTagsId(Posts posts) {

        for (int i = 0; i < posts.getPostTagsList().size(); i++) {
            posts.getPostTagsList().get(i).getTags().setName(
                    tagService.findVerifiedTags(posts.getPostTagsList().
                            get(i).getTags().getTagsId()).getName()
            );
        }
        return posts;
    }
}

