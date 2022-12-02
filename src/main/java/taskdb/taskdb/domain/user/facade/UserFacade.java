package taskdb.taskdb.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.UserException;
import taskdb.taskdb.domain.user.exception.UserExceptionType;
import taskdb.taskdb.domain.user.service.auth.EmailService;
import taskdb.taskdb.global.security.jwt.SecurityUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private static final int RANK_SIZE = 10;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

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

    public User checkNotJoinByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_SIGNUP_EMAIL));
    }

    public void checkCorrectPassword(User user, String password) {
        if(user.isNotCorrectPassword(passwordEncoder, password)) {
            throw new UserException(UserExceptionType.WRONG_PASSWORD);
        }
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new UserException(UserExceptionType.REQUIRED_DO_LOGIN));
    }

    public void checkDifferentUser(User user, User writer) {
        String email = writer.getEmail();
        if(user.isNotCorrectEmail(email)) {
            throw new UserException(UserExceptionType.DIFFERENT_USER);
        }
    }

    public List<User> getUsersByContributionLevel() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getContributionLevel).reversed()
                        .thenComparing(User::getAnswerCount).reversed()
                        .thenComparing(User::getQuestionCount).reversed())
                .limit(RANK_SIZE)
                .collect(Collectors.toList());
    }
}
