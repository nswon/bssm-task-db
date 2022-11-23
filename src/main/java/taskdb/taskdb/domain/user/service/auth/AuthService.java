package taskdb.taskdb.domain.user.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;
import taskdb.taskdb.domain.user.presentation.dto.auth.request.UserLoginRequestDto;
import taskdb.taskdb.domain.user.presentation.dto.auth.response.TokenResponseDto;
import taskdb.taskdb.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponseDto login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_SIGNUP_EMAIL));

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new UserException(UserExceptionType.WRONG_PASSWORD);
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole().name());

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }
}
