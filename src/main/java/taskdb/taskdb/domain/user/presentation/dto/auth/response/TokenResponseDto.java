package taskdb.taskdb.domain.user.presentation.dto.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.Cookie;

@Getter
@NoArgsConstructor
public class TokenResponseDto {
    private Cookie cookie;

    @Builder
    public TokenResponseDto(Cookie cookie) {
        this.cookie = cookie;
    }
}
