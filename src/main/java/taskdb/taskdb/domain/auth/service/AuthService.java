package taskdb.taskdb.domain.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.auth.exception.InvalidPasswordException;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.auth.dto.UserLoginRequestDto;
import taskdb.taskdb.domain.auth.dto.TokenResponseDto;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.global.security.jwt.JwtTokenProvider;
import taskdb.taskdb.mapper.UserMapper;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserReader userReader;
    private final UserMapper userMapper;

    public TokenResponseDto login(UserLoginRequestDto requestDto) {
        User user = userReader.getUserByEmail(requestDto.getEmail());
        checkPassword(requestDto, user);
        String accessToken = jwtTokenProvider.createAccessToken(user);
        return userMapper.of(accessToken);
    }

    private void checkPassword(UserLoginRequestDto requestDto, User user) {
        String requestPassword = requestDto.getPassword();
        String userPassword = user.getPassword();
        if(!passwordEncoder.matches(requestPassword, userPassword)) {
            throw new InvalidPasswordException();
        }
    }
}
