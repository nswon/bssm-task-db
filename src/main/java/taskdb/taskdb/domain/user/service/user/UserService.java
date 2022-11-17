package taskdb.taskdb.domain.user.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;
import taskdb.taskdb.domain.user.presentation.dto.user.request.UserJoinRequestDto;
import taskdb.taskdb.domain.user.service.EmailService;


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
            throw new UserException(UserExceptionType.ALREADY_EXIST_EMAIL);
        }

        if(emailService.verityCode(requestDto.getCheckCode())) {
            throw new UserException(UserExceptionType.WRONG_EMAIL_CHECK_CODE);
        }

        User user = userRepository.save(requestDto.toEntity());
        user.encodedPassword(passwordEncoder);
        user.addUserAuthority();
        return true;
    }
}
