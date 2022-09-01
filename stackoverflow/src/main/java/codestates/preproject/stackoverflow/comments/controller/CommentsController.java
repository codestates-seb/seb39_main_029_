package codestates.preproject.stackoverflow.comments.controller;

import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.comments.mapper.CommentsMapper;
import codestates.preproject.stackoverflow.comments.repository.CommentsRepository;
import codestates.preproject.stackoverflow.comments.service.CommentsService;
import codestates.preproject.stackoverflow.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Valid
@RequestMapping("/v1/comments")
public class CommentsController {
    private final CommentsRepository commentsRepository;

    private final CommentsService commentsService;

    private final CommentsMapper commentsMapper;

    public CommentsController(CommentsRepository commentsRepository, CommentsService commentsService, CommentsMapper commentsMapper){
        this.commentsRepository = commentsRepository;
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

    @PostMapping
    public ResponseEntity postComments(@Valid @RequestBody CommentsDto.Post post){
        Comments comments = commentsMapper.commentsPostDtoToComments(post);
        commentsService.createComments(comments);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{comments-id}")
    public ResponseEntity deleteComments(@PathVariable("comments-id") @Positive long commentsid,
                                         @RequestParam long memberid){
        commentsService.deleteComments(commentsid, memberid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update/{comments-id}")
    public ResponseEntity patchComments(@PathVariable("comments-id") @Positive long commentsid,
                                        @Valid @RequestBody CommentsDto.Patch patch){
        patch.setCommentsid(commentsid);
        Comments comments = commentsMapper.commentsPatchDtoToComments(patch);
        commentsService.updateComments(comments);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/upvotes/{comments-id}")
    public ResponseEntity upVotesComments(@PathVariable("comments-id") @Positive long commentsid,
                                        @RequestParam long memberid){
        Comments comments = commentsService.UvotesComments(commentsid, memberid);
        CommentsDto.Response response = commentsMapper.CommentsToCommentsResponseDto(comments);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PatchMapping("/downvotes/{comments-id}")
    public ResponseEntity downVotesComments(@PathVariable("comments-id") @Positive long commentsid,
                                        @RequestParam long memberid){
        Comments comments = commentsService.DvotesComments(commentsid, memberid);
        CommentsDto.Response response = commentsMapper.CommentsToCommentsResponseDto(comments);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getsComments(@RequestParam long postsid){
        List<CommentsDto.Response> list = commentsService.getsComments(postsid);
        return new ResponseEntity(list,HttpStatus.OK);
    }
}
