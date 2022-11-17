package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.presentation.dto.request.UserJoinRequestDto;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    public boolean join(UserJoinRequestDto requestDto) {
        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 사용자입니다.");
        }

        if(emailService.verityCode(requestDto.getCheckCode())) {
            throw new IllegalArgumentException("이메일 인증 코드가 일치하지 않습니다.");
        }

        User user = userRepository.save(requestDto.toEntity());
        user.encodedPassword(passwordEncoder);
        user.addUserAuthority();
        return true;
    }
}
