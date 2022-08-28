package codestates.preproject.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    EMAIL_NOT_FOUND(404, "Email not found"),
    PASSWORD_NOT_FOUND(404, "Password wrong"),
    NICKNAME_EXISTS(409, "NickName is exists"),
    INVALID_MEMBER_STATUS(400, "Invalid member status"),  // TO 추가된 부분
    POST_NOT_FOUND(404,"Post not found"),
    POST_EXISTS(409, "Post is exists"),
    COMMENTS_NOT_FOUND(404,"Comments not found"),

    TAGS_NOT_FOUND(404,"Tag not found"),

    COMMENTS_MEMBER_NOT_SAME(404, "Member is not Owner"),
    VOTES_ALREADY(404,"Votes Already"),
    NOT_VOTES(404,"Not Vote");



    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
