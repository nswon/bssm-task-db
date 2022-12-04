package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserJoinRequestDto {
    private String email;
    private String checkCode;
    private int grade;
    private String nickname;
    private String password;

    public UserJoinRequestDto() {
    }

    public UserJoinRequestDto(String email, String checkCode, int grade, String nickname, String password) {
        this.email = email;
        this.checkCode = checkCode;
        this.grade = grade;
        this.nickname = nickname;
        this.password = password;
    }
}
