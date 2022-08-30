package codestates.preproject.stackoverflow.post.service;

import codestates.preproject.stackoverflow.comments.service.CommentsService;
import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.service.MemberService;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.mapper.PostMapper;
import codestates.preproject.stackoverflow.post.repository.PostRepository;
import codestates.preproject.stackoverflow.pvote.entity.Pvote;
import codestates.preproject.stackoverflow.pvote.service.PVoteService;
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

    private final PVoteService pVoteService;

    public PostService(TagService tagService, PostRepository postRepository, MemberService memberService,
                       PostMapper postMapper, CommentsService commentsService, PVoteService pVoteService) {
        this.tagService = tagService;
        this.postRepository = postRepository;
        this.memberService = memberService;
        this.postMapper = postMapper;
        this.commentsService = commentsService;
        this.pVoteService = pVoteService;
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
                        post.getPostTagsList().get(i).setTags(
                                tag.get(i).getTags()
                        );

                        post.getPostTagsList().get(i).getTags().setName(
                                tagService.findVerifiedTags(tag.get(i).getTags().getTagsId()).getName()
                        );

                    }
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

    //태그 클릭 페이지에서 question 페이지 이동시 사용하는 service 메서드
    public List<Posts> tagsCheck(List<Posts> posts, Long tagCheckId) {
        List<Posts> result = new ArrayList<>();

        Tags tag = tagService.findVerifiedTags(tagCheckId);

        for (int i = 0; i < posts.size(); i++) {
            for (int j = 0; j < tag.getPostTags().size(); j++) {
                if (tag.getPostTags().get(j).getPosts()==(posts.get(i))) {
                    result.add(posts.get(i));
                }
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
    //상수가 추가한 posts 서비스 코드 입니다.
    public List<PostDto.uResponse> findUserPosts(long memberid, int page, int size){
        List<Posts> postsList = postRepository.findByMemberid(memberid, size*(page-1), size);
        List<Posts> newList = new ArrayList<>();
        for(Posts posts : postsList){
            newList.add(findTagsId(posts));
        }
        List<PostDto.uResponse> result = postMapper.PostsToPostuResponseDto(newList);
        return result;
    }

    public void voteUpMember(long memberId) {
        memberService.findVerifiedMember(memberId);
        memberService.updateRep(memberId);
    }
    public void voteDownMember(long memberId){
        memberService.findVerifiedMember(memberId);
        memberService.downRep(memberId);
    }
    public Posts upVotes(long postId, long memberId) {
        Pvote pvote=pVoteService.findPVote(postId, memberId);
        if (pvote == null) {
            Posts post =findVerifiedPosts(postId);
            post.setVotes(post.getVotes()+1);
            voteUpMember(post.getMember().getMemberid());
            pVoteService.saveVotes(memberId,post);
            post.setIsvote(true);
            return postRepository.save(post);
        }else{
            throw new BusinessLogicException(ExceptionCode.VOTES_ALREADY);
        }
    }

    public Posts downVotes(long postId, long memberId) {
        Pvote pvote=pVoteService.findPVote(postId, memberId);
        if (pvote != null) {
            Posts post =findVerifiedPosts(postId);
            post.setVotes(post.getVotes()-1);
            voteDownMember(post.getMember().getMemberid());
            pVoteService.deleteVotes(pvote);
            post.setIsvote(false);
            return postRepository.save(post);
        }else{
            throw new BusinessLogicException(ExceptionCode.NOT_VOTES);
        }
    }

    public List<Posts> findPostsByWord(String word){
        StringBuilder sb = new StringBuilder(word);
        sb.insert(0,"%");
        sb.append("%");
        word = sb.toString().toLowerCase();
        List<Posts> list = postRepository.findByWord(word);
        return list;
    }

    public List<Posts> findLimit(List<Posts> posts, int page, int size){
        List<Posts> result = new ArrayList<>();
        int count =0;
        for(int i=(page-1)*size; i<posts.size(); i++){
            if(count == size) break;
            result.add(posts.get(i));
            count++;
        }
        return result;
    }

//    Other Solution
//    public Page<Posts> findTests(int page, int size, String word){
//        StringBuilder sb = new StringBuilder(word);
//        sb.insert(0,"%");
//        sb.append("%");
//        word = sb.toString().toLowerCase();
//        return postRepository.findAllTest(PageRequest.of(page, size), word);
//    }
}

