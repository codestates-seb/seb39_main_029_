package codestates.preproject.stackoverflow.post.controller;

import codestates.preproject.stackoverflow.dto.MultiResponseDto;
import codestates.preproject.stackoverflow.dto.SingleResponseDto;
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
public class PostController {
    private final PostMapper mapper;
    private final PostService postService;

    public PostController(PostMapper mapper, PostService postService) {
        this.mapper = mapper;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity makePost(@Valid @RequestBody PostDto.Post post) {
        Posts posts = postService.createPost(mapper.makePostsToPosts(post));
        PostDto.Response response = mapper.PostsToResponse(posts);

        return new ResponseEntity( new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{post-id}")
    public ResponseEntity PatchPost(
            @PathVariable("post-id") @Positive long postId,
            @Valid @RequestBody PostDto.Patch requestBody
            ) {
        requestBody.setPostId(postId);

        Posts posts = postService.updatePost(mapper.PatchPostsToPosts(requestBody));

        return new ResponseEntity( new SingleResponseDto<>(posts), HttpStatus.OK);
    }

    @GetMapping("/{post-id}")
    public ResponseEntity getPost(
            @PathVariable("post-id") @Positive long postId) {
        Posts posts = postService.findPost(postId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.PostsToResponse(posts))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getPosts(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Posts> pagePosts = postService.findPosts(page - 1, size);
        List<Posts> members = pagePosts.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.PostsToResponses(members),
                        pagePosts),
                HttpStatus.OK);
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(
            @PathVariable("post-id") @Positive long postId) {
        postService.deletePost(postId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
