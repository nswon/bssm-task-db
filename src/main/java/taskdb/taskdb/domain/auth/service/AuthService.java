package taskdb.taskdb.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.auth.presentation.dto.request.UserLoginRequestDto;
import taskdb.taskdb.domain.auth.presentation.dto.response.TokenResponseDto;
import taskdb.taskdb.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    public TokenResponseDto login(UserLoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        User user = userFacade.getUserByEmail(email);
        String password = requestDto.getPassword();
        userFacade.checkCorrectPassword(user, password);

        String accessToken = jwtTokenProvider.createAccessToken(user);
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
