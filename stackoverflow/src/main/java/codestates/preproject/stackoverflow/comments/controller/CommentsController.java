package codestates.preproject.stackoverflow.comments.controller;

import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.comments.mapper.CommentsMapper;
import codestates.preproject.stackoverflow.comments.repository.CommentsRepository;
import codestates.preproject.stackoverflow.comments.service.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        return null;
    }
}
