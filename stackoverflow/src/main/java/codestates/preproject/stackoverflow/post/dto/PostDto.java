package codestates.preproject.stackoverflow.post.dto;


import codestates.preproject.stackoverflow.comments.entity.Comments;

import codestates.preproject.stackoverflow.tags.entity.Tags;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String subject;

        @Positive
        private long memberId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        private List<Tags> tag;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private long postId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String subject;

        @Positive
        private long memberId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        private List<Tags> tag;

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
        private List<TagsResponse> tag;
        private int vote;
        private LocalDateTime createAt;
        private List<Comments> commentsList;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class TagsResponse {
        private long tageId;
        private String data;
        private long postsId;
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
}
