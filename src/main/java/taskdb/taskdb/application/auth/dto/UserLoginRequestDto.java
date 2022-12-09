package taskdb.taskdb.application.auth.dto;

import lombok.Getter;
import lombok.ToString;

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
