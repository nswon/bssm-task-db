package taskdb.taskdb.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.domain.UserRepository;
import taskdb.taskdb.domain.user.exception.*;
import taskdb.taskdb.domain.auth.service.EmailService;
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
            throw new DuplicateEmailException();
        }
    }

    public void checkCorrectEmailCheckCode(String checkCode) {
        if(emailService.verityCode(checkCode)) {
            throw new InvalidAuthCodeException();
        }
    }

    public User checkNotJoinByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(InvalidEmailException::new);
    }

    public void checkCorrectPassword(User user, String password) {
        if(user.isNotCorrectPassword(passwordEncoder, password)) {
            throw new InvalidPasswordException();
        }
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(UserNotFoundException::new);
    }

    public void checkDifferentUser(User user, User writer) {
        String email = writer.getEmail();
        if(user.isNotCorrectEmail(email)) {
            throw new DifferentUserException();
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
