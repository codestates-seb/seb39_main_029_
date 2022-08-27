package codestates.preproject.stackoverflow.post.service;

import codestates.preproject.stackoverflow.comments.service.CommentsService;
import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.service.MemberService;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.mapper.PostMapper;
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
    //상수가 추가한 코드 입니다.
    private final PostMapper postMapper;
    private final CommentsService commentsService;

    public PostService(TagService tagService, PostRepository postRepository, MemberService memberService,
                       PostMapper postMapper, CommentsService commentsService) {
        this.tagService = tagService;
        this.postRepository = postRepository;
        this.memberService = memberService;
        this.postMapper = postMapper;
        this.commentsService = commentsService;
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
        //상수가 추가한 코드 입니다.
        Optional.ofNullable(posts.getCommentsCount())
                .ifPresent(count -> post.setCommentsCount(count));

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

    //상수가 추가한 posts 서비스 코드 입니다.
    public List<PostDto.uResponse> findUserPosts(long memberid, int page, int size){
        List<Posts> postsList = postRepository.findByMemberid(memberid, size*(page-1), size);
        List<PostDto.uResponse> result = postMapper.PostsToPostuResponseDto(postsList);
        return result;
    }
}

