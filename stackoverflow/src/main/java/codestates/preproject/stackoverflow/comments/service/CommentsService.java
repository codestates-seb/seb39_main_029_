package codestates.preproject.stackoverflow.comments.service;

import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.comments.repository.CommentsRepository;
import codestates.preproject.stackoverflow.member.service.MemberService;
import codestates.preproject.stackoverflow.post.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentsService {
    private final MemberService memberService;

    private final PostService postService;

    private final CommentsRepository commentsRepository;

    public CommentsService(MemberService memberService, PostService postService, CommentsRepository commentsRepository){
        this.memberService = memberService;
        this.postService = postService;
        this.commentsRepository = commentsRepository;
    }


    public Comments createComments(Comments comments){
        verifyComments(comments);
        Comments saved = commentsRepository.save(comments);
        return saved;
    }

    public void verifyComments(Comments comments){
        memberService.findVerifiedMember(comments.getMember().getMemberid());
        postService.findPost(comments.getPosts().getPostId());
    }
}
