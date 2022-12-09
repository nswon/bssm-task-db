package taskdb.taskdb.adapter.auth.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.application.auth.dto.UserLoginRequestDto;
import taskdb.taskdb.application.auth.dto.TokenResponseDto;
import taskdb.taskdb.application.auth.port.in.AuthLoginUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthLoginUseCase authLoginUseCase;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authLoginUseCase.login(requestDto);
    }
}