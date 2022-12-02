package taskdb.taskdb.domain.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import taskdb.taskdb.domain.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ToString
public class EmailDto {

    @NotNull(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    public User toEntity() {
        return User.builder()
                .email(email)
                .build();
    }
}
