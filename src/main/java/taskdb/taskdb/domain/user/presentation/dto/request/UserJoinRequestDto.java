package taskdb.taskdb.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import taskdb.taskdb.domain.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    public UserJoinRequestDto(String email, int grade, String nickname, String password) {
        this.email = email;
        this.grade = grade;
        this.nickname = nickname;
        this.password = password;
    }
}
