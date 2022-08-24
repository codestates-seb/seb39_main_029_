package codestates.preproject.stackoverflow.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {
    @Getter
    @AllArgsConstructor // TODO 테스트를 위해 추가됨
    public static class Post {
        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
        private String nickName;

        @NotBlank(message = "비밀번호는 공백이 아니어야 합니다.")
        private String password;

        @NotBlank
        @Email
        private String email;
    }

    @AllArgsConstructor
    @Getter
    public static class response {
    }
}
