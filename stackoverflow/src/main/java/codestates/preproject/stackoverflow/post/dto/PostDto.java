package codestates.preproject.stackoverflow.post.dto;

import codestates.preproject.stackoverflow.comments.dto.CommentsDto;
import codestates.preproject.stackoverflow.comments.entity.Comments;

import codestates.preproject.stackoverflow.tags.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String subject;

        @Positive
        private long memberId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;
        @Valid
        private List<PostTagDto> postTag;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {

        private long postId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String subject;

        @Positive
        private long memberId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;
        @Valid
        private List<PostTagDto> postTag;

        public void setPostId(long postId) {
            this.postId = postId;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Response {
        private long postId;
        private String subject;
        private long memberId;
        private String content;
        private List<TagsResponse> postTag;
        private int votes;
        private LocalDateTime createAt;
        private List<CommentsDto.Response> commentsList;
        private int commentsCount;
        private String nickName;
        private int reputation;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class TagsResponse {
        private long tagId;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class PostTagDto {

        private long tagId;
    }

    //상수가 작성한 코드 입니다.
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class uResponse{
        private long postId;
        private String subject;
        private int vote;
        private List<TagsResponse> tag;
        private int commentsCount;
        private LocalDateTime createAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class voteResponse{
        private long postId;
        private String subject;
        private long memberId;
        private String content;
        private List<TagsResponse> postTag;
        private int votes;
        private LocalDateTime createAt;
        private List<CommentsDto.Response> commentsList;
        private int commentsCount;
        private int reputation;
        private boolean isvote;
    }
}
