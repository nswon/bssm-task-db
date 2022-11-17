package taskdb.taskdb.domain.user.presentation.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskdb.taskdb.domain.user.presentation.dto.auth.request.UserLoginRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.auth.response.TokenResponseDto;
import taskdb.taskdb.domain.user.service.auth.AuthService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authService.login(requestDto);
    }
}