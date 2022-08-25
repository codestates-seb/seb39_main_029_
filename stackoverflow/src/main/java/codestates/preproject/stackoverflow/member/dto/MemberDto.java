package codestates.preproject.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {


    @Getter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Join {

        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String nickName;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        private String password;

        @NotBlank
        @Email
        private String email;
    }


    @Getter
    @AllArgsConstructor
    public static class Login{
        private int memberid;

        @NotBlank
        @Email
        private String email;

        @NotBlank(message = "비밀번호 입력")
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Patch {
        private long memberid;

        private String nickName;

        private String selfId;

        private String location;

        private String title;
    }

    @Getter
    @Setter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Response {
        private long memberid;

        private String nickName;

        private String selfId;

        private String location;

        private String title;

        private Integer reputation;


    }
}
