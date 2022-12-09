package taskdb.taskdb.application.auth.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmailRequestDto {
    private String email;

    public EmailRequestDto() {
    }

    public EmailRequestDto(String email) {
        this.email = email;
    }
}
