package taskdb.taskdb.application.user.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.auth.dto.TokenResponseDto;
import taskdb.taskdb.domain.user.entity.*;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto of(User user) {
        return new UserResponseDto(user);
    }

    public User of(UserJoinRequestDto requestDto) {
        return User.builder()
                .email(Email.of(requestDto.getEmail()))
                .grade(Grade.of(requestDto.getGrade()))
                .nickname(Nickname.of(requestDto.getNickname()))
                .bio(Bio.createDefault())
                .password(Password.of(passwordEncoder, requestDto.getPassword()))
                .image(Image.createDefault())
                .role(Role.ROLE_USER)
                .build();
    }

    public TokenResponseDto of(String accessToken, String refreshToken) {
        return new TokenResponseDto(accessToken, refreshToken);
    }
}
