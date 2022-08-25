package codestates.preproject.stackoverflow.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    EMAIL_NOT_FOUND(404, "Email not found"),
    PASSWORD_NOT_FOUND(404, "Password wrong"),
    NICKNAME_EXISTS(409, "NickName is exists"),
    INVALID_MEMBER_STATUS(400, "Invalid member status");  // TO 추가된 부분

    @Getter
    private int status;

    @Getter
    private String message;

    @Getter
    private String test = "이거다";

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
