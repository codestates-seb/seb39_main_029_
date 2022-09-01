package codestates.preproject.stackoverflow.tags.dto;

import codestates.preproject.stackoverflow.post.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class TagsDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Post {

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String name;

        @NotBlank
        private String content;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class Response {
        private long tagsId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String name;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class TagsCountResponse {
        private long tagsId;

        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String name;

        private Long count;

        private String content;

    }
}
