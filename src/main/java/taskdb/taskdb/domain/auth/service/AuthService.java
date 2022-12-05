package taskdb.taskdb.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.auth.exception.InvalidPasswordException;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.facade.UserFacade;
import taskdb.taskdb.domain.auth.dto.UserLoginRequestDto;
import taskdb.taskdb.domain.auth.dto.TokenResponseDto;
import taskdb.taskdb.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDto login(UserLoginRequestDto requestDto) {
        User user = userFacade.getUserByEmail(requestDto);
        checkPassword(requestDto, user);
        String accessToken = jwtTokenProvider.createAccessToken(user);
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    private void checkPassword(UserLoginRequestDto requestDto, User user) {
        String requestPassword = requestDto.getPassword();
        String userPassword = user.getPassword();
        if(!passwordEncoder.matches(requestPassword, userPassword)) {
            throw new InvalidPasswordException();
        }
    }
}
