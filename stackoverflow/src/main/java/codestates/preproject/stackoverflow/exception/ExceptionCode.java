package codestates.preproject.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    EMAIL_NOT_FOUND(404, "Email not found"),
    PASSWORD_NOT_FOUND(404, "Password wrong"),
    NICKNAME_EXISTS(409, "NickName is exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
