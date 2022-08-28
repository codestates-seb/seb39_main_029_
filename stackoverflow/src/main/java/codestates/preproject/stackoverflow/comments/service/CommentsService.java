package codestates.preproject.stackoverflow.comments.service;

//import codestates.preproject.stackoverflow.checkvote.Checkvote;
import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.comments.mapper.CommentsMapper;
import codestates.preproject.stackoverflow.comments.repository.CommentsRepository;
import codestates.preproject.stackoverflow.cvote.service.CvoteService;
import codestates.preproject.stackoverflow.exception.BusinessLogicException;
import codestates.preproject.stackoverflow.exception.ExceptionCode;
import codestates.preproject.stackoverflow.member.entity.Member;
import codestates.preproject.stackoverflow.member.service.MemberService;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.service.PostService;
import org.springframework.context.annotation.Lazy;
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

    private final CvoteService cvoteService;

    private final CommentsMapper commentsMapper;

    public CommentsService(MemberService memberService, @Lazy PostService postService,
                           CommentsRepository commentsRepository, CvoteService cvoteService,
                           CommentsMapper commentsMapper){
        this.memberService = memberService;
        this.postService = postService;
        this.commentsRepository = commentsRepository;
        this.cvoteService = cvoteService;
        this.commentsMapper = commentsMapper;
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
    //코멘트 리스트들 가져오기
    public List<CommentsDto.Response> getsComments(long postsid){
        List<Comments> commentsList = commentsRepository.findByPostsId(postsid);
        List<CommentsDto.Response> result = commentsMapper.CommentsToCommentsResponseDtos(commentsList);
        return result;
    }

    public Comments UvotesComments(long commentsid, long votememberid){
        Comments comments = findVerifiedComments(commentsid);
        boolean isvote = cvoteService.findCvote(votememberid,commentsid);
        if(isvote == false){
            cvoteService.saveCvote(votememberid,comments);
            long memeberid = comments.getMember().getMemberid();
            UcheckMember(memeberid);
            int count = comments.getVotes() + 1;
            comments.setVotes(count);
            comments.setIsvote(true);
            return commentsRepository.save(comments);
        }else{
            throw new BusinessLogicException(ExceptionCode.VOTES_ALREADY);
        }
    }

    public Comments DvotesComments(long commentsid, long votememberid){
        Comments comments = findVerifiedComments(commentsid);
        boolean isvote = cvoteService.findCvote(votememberid,commentsid);
        if(isvote){
            cvoteService.deleteCvote(votememberid,comments);
            long memeberid = comments.getMember().getMemberid();
            DcheckMember(memeberid);
            int count = comments.getVotes() - 1;
            comments.setVotes(count);
            comments.setIsvote(false);
            return commentsRepository.save(comments);
        }else{
            throw new BusinessLogicException(ExceptionCode.NOT_VOTES);
        }
    }

    public void UcheckMember(long memberid){
        memberService.findVerifiedMember(memberid);
        memberService.updateRep(memberid);
    }

    public void DcheckMember(long memberid){
        memberService.findVerifiedMember(memberid);
        memberService.downRep(memberid);
    }

    public void deleteComments(long commentsid, long memberid){
        Comments comments = findVerifiedComments(commentsid);
        if(comments.getMember().getMemberid() != memberid){
            throw new BusinessLogicException(ExceptionCode.COMMENTS_MEMBER_NOT_SAME);
        }
        Posts posts = comments.getPosts();
        posts.setCommentsCount(posts.getCommentsCount()-1);
        postService.updatePost(posts);

        commentsRepository.delete(comments);
    }

    public void verifyComments(Comments comments){
        memberService.findVerifiedMember(comments.getMember().getMemberid());
        Posts posts = postService.findPost(comments.getPosts().getPostId());
        posts.setCommentsCount(posts.getCommentsCount()+1);
        postService.updatePost(posts);
    }

    public Comments findVerifiedComments(long commentsid){
        Optional<Comments> findComments = commentsRepository.findById(commentsid);
        Comments comments = findComments.orElseThrow(()-> new BusinessLogicException(ExceptionCode.COMMENTS_NOT_FOUND));
        return comments;
    }
}
