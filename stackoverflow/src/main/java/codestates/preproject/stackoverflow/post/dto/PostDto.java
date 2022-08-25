package codestates.preproject.stackoverflow.post.dto;

import codestates.preproject.stackoverflow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import java.util.List;

public class PostDto {

    @Getter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Post {
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String subject;

        @Positive
        private long memberId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        private List<Integer> tag;
    }

    @Getter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Patch {

        private long postId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String subject;

        @Positive
        private long memberId;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String content;

        private List<Integer> tag;

        public void setPostId(long postId) {
            this.postId = postId;
        }
    }

    @Getter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Response {
        private long postId;
        private String subject;
        private long memberId;
        private String content;
        private List<String> tag;
        private int vote;

    }
}
