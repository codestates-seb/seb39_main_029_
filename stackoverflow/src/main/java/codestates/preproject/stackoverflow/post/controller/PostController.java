package codestates.preproject.stackoverflow.post.controller;

import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;
import codestates.preproject.stackoverflow.comments.service.CommentsService;
import codestates.preproject.stackoverflow.dto.MultiResponseDto;
import codestates.preproject.stackoverflow.post.dto.PostDto;
import codestates.preproject.stackoverflow.post.entity.Posts;
import codestates.preproject.stackoverflow.post.mapper.PostMapper;
import codestates.preproject.stackoverflow.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/posts")
@Validated
@Slf4j
@CrossOrigin(origins = {"https://localhost:3000","https://localhost:3001","localhost:3000","localhost:3001"},
        allowedHeaders = {"POST","GET","PATCH","DELETE"})
public class PostController {

    private final CommentsService commentsService;
    private final PostMapper mapper;
    private final PostService postService;

    public PostController(CommentsService commentsService, PostMapper mapper, PostService postService) {
        this.commentsService = commentsService;
        this.mapper = mapper;
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity makePost(@Valid @RequestBody PostDto.Post post) {
        Posts posts = postService.createPost(mapper.makePostsToPosts(post));
        PostDto.Response response = mapper.PostsToResponse(posts);

        return new ResponseEntity( response, HttpStatus.CREATED);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity PatchPost(
            @PathVariable("post-id") @Positive long postId,
            @Valid @RequestBody PostDto.Patch requestBody
            ) {
        requestBody.setPostId(postId);
        Posts posts = postService.updatePost(mapper.PatchPostsToPosts(requestBody));
        return new ResponseEntity<>( mapper.PostsToResponse(posts), HttpStatus.OK);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity getPost(
            @PathVariable("post-id") @Positive long postId) {
        Posts posts = postService.findPost(postId);
        List<CommentsDto.Response> result = commentsService.getsComments(postId);
        PostDto.Response response = mapper.PostsToResponse(posts);
        response.setCommentsList(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPosts(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size,
                                   @RequestParam String arrange,
                                   @RequestParam Long tagCheckId
    ) {
        Page<Posts> pagePosts = postService.findPosts(page - 1, size,arrange);
        List<Posts> members = pagePosts.getContent();
        if (tagCheckId != -1) {
            members = postService.tagsCheck(members, tagCheckId);
        }
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.PostsToResponses(members), pagePosts),
                HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(
            @PathVariable("post-id") @Positive long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/upVotes/{post-id}")
    public ResponseEntity upVotes(
            @PathVariable("post-id") @Positive long postId,
            @RequestParam @Positive long memberId
            ) {
       Posts post= postService.upVotes(postId, memberId);
       return new ResponseEntity(mapper.PostsToVoteResponse(post), HttpStatus.OK);
    }

    @PatchMapping("/downVotes/{post-id}")
    public ResponseEntity downVotes(
            @PathVariable("post-id") @Positive long postId,
            @RequestParam @Positive long memberId
    ) {
        Posts post= postService.downVotes(postId, memberId);
        PostDto.voteResponse response = mapper.PostsToVoteResponse(post);
        return new ResponseEntity(response, HttpStatus.OK);
    }

//    @GetMapping("/word")
//    public ResponseEntity getPostsByWord(@Positive @RequestParam int page,
//                                   @Positive @RequestParam int size,
//                                   @RequestParam String arrange,
//                                   @RequestParam Long tagCheckId
//    ) {
//        List<Posts> members = postService.findPostsByWord(page,size,arrange);
//        if (tagCheckId != -1) {
//            members = postService.tagsCheck(members, tagCheckId);
//        }
//        return new ResponseEntity<>(new MultiResponseDto<>(mapper.PostsToResponses(members), null),
//                HttpStatus.OK);
//    }
}
