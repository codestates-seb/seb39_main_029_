package codestates.preproject.stackoverflow.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommentsDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private long userid;

        private long postid;

        private String contents;
    }
}
