package taskdb.taskdb.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import taskdb.taskdb.domain.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@ToString
public class UserLoginRequestDto {
    private String email;
    private String password;

    public UserLoginRequestDto() {
    }

    public UserLoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
