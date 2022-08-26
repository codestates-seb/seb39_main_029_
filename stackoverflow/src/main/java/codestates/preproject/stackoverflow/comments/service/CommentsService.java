package codestates.preproject.stackoverflow.comments.service;

//import codestates.preproject.stackoverflow.checkvote.Checkvote;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.comments.repository.CommentsRepository;
import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.service.MemberService;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Comments updateComments(Comments comments){
        Comments findComments = findVerifiedComments(comments.getCommentsid());
        Optional.ofNullable(comments.getContent()).ifPresent(content -> findComments.setContent(content));
        return commentsRepository.save(findComments);
    }

    public Comments votesComments(long commentsid){
        Comments comments = findVerifiedComments(commentsid);
        checkMember(comments.getMember().getMemberid());

        int count = comments.getVotes() + 1;
        comments.setVotes(count);

        return commentsRepository.save(comments);
    }

    public void checkMember(long memberid){
        memberService.findVerifiedMember(memberid);
        memberService.updateRep(memberid);
    }

    public void deleteComments(long commentsid){
        Comments comments = findVerifiedComments(commentsid);
        commentsRepository.delete(comments);
    }

    public void verifyComments(Comments comments){
        memberService.findVerifiedMember(comments.getMember().getMemberid());
        postService.findPost(comments.getPosts().getPostId());
    }

    public Comments findVerifiedComments(long commentsid){
        Optional<Comments> findComments = commentsRepository.findById(commentsid);
        Comments comments = findComments.orElseThrow(()-> new BusinessLogicException(ExceptionCode.COMMENTS_NOT_FOUND));
        return comments;
    }
}
