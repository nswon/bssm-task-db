package taskdb.taskdb.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;
import taskdb.taskdb.domain.user.service.auth.EmailService;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public void checkAvailableEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserException(UserExceptionType.ALREADY_EXIST_EMAIL);
        }
    }

    public void checkCorrectEmailCheckCode(String checkCode) {
        if(emailService.verityCode(checkCode)) {
            throw new UserException(UserExceptionType.WRONG_EMAIL_CHECK_CODE);
        }
    }
}
