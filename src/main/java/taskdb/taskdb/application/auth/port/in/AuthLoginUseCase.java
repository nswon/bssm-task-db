package taskdb.taskdb.application.auth.port.in;

import taskdb.taskdb.application.auth.dto.TokenResponseDto;
import taskdb.taskdb.application.auth.dto.UserLoginRequestDto;

public interface AuthLoginUseCase {
    TokenResponseDto login(UserLoginRequestDto requestDto);
}
