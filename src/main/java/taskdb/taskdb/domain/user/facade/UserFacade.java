package taskdb.taskdb.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.auth.exception.LoginFailedException;
import taskdb.taskdb.domain.user.domain.Email;
import taskdb.taskdb.domain.user.domain.Password;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.repository.UserRepository;
import taskdb.taskdb.domain.user.exception.*;
import taskdb.taskdb.domain.auth.service.EmailService;
import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
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

    public void validate(UserJoinRequestDto requestDto) {
        checkAvailableEmail(requestDto);
        checkCorrectEmailCheckCode(requestDto);
    }

    private void checkAvailableEmail(UserJoinRequestDto requestDto) {
        if(userRepository.findByEmailValue(requestDto.getEmail()).isPresent()) {
            throw new DuplicateEmailException();
        }
    }

    private void checkCorrectEmailCheckCode(UserJoinRequestDto requestDto) {
        if(emailService.verityCode(requestDto.getCheckCode())) {
            throw new InvalidAuthCodeException();
        }
    }

    public User getUserByEmailAndPassword(String email, String encodedPassword) {
        Password password = Password.of(passwordEncoder, encodedPassword);
        return userRepository.findByEmailValueAndPasswordValue(email, password.getValue())
                .orElseThrow(LoginFailedException::new);
    }

    public User getCurrentUser() {
        return userRepository.findByEmailValue(SecurityUtil.getLoginUserEmail())
                .orElseThrow(AuthorizationException::new);
    }

    public void checkDifferentUser(User user, User writer) {
        Email email = writer.getEmail();
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
