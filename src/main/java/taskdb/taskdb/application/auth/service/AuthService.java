package taskdb.taskdb.application.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.auth.port.in.AuthLoginUseCase;
import taskdb.taskdb.application.auth.port.out.SaveRefreshTokenPort;
import taskdb.taskdb.domain.auth.exception.InvalidPasswordException;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.auth.dto.UserLoginRequestDto;
import taskdb.taskdb.application.auth.dto.TokenResponseDto;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.infrastructure.security.jwt.JwtTokenProvider;
import taskdb.taskdb.application.user.dto.UserMapper;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService implements AuthLoginUseCase {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final GetUserPort getUserPort;
    private final SaveRefreshTokenPort saveRefreshTokenPort;
    private final UserMapper userMapper;

    @Override
    public TokenResponseDto login(UserLoginRequestDto requestDto) {
        User user = getUserPort.getUserByEmail(requestDto.getEmail());
        checkPassword(requestDto.getPassword(), user);
        final String accessToken = jwtTokenProvider.createAccessToken(user.getEmail());
        final String refreshToken = jwtTokenProvider.createRefreshToken();
        saveRefreshTokenPort.save(user.getEmail(), refreshToken);
        return userMapper.of(accessToken, refreshToken);
    }

    private void checkPassword(String password, User user) {
        String userPassword = user.getPassword();
        if(!passwordEncoder.matches(password, userPassword)) {
            throw new InvalidPasswordException();
        }
    }
}
