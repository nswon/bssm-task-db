package taskdb.taskdb.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponseDto {
    private String accessToken;

    public TokenResponseDto() {
    }

    @Builder
    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
