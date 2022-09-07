package codestates.preproject.stackoverflow.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentsDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private long userid;

        private long postid;

        private String contents;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch{
        private long commentsid;

        private String content;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private long commentsid;

        private int votes;

        private String nickName;

        private String content;

        private long memberid;

        private int reputation;

        private boolean isvote;
    }
}
