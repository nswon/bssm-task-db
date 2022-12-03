package taskdb.taskdb.domain.auth.dto;

import lombok.Getter;

@Getter
public class EmailResponseDto {
    private String message;

    public EmailResponseDto() {
    }

    public EmailResponseDto(String message) {
        this.message = message;
    }
}
