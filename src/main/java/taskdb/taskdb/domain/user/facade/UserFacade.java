package taskdb.taskdb.domain.user.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.auth.dto.UserLoginRequestDto;
import taskdb.taskdb.domain.auth.exception.InvalidEmailException;
import taskdb.taskdb.domain.user.domain.Email;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.repository.UserRepository;
import taskdb.taskdb.domain.user.exception.*;
import taskdb.taskdb.domain.auth.service.EmailService;
import taskdb.taskdb.domain.user.dto.UserJoinRequestDto;
import taskdb.taskdb.global.security.jwt.SecurityUtil;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public void validate(UserJoinRequestDto requestDto) {
        checkUniqueEmail(requestDto);
        checkCorrectEmailCheckCode(requestDto);
        checkUniqueNickname(requestDto);
    }

    private void checkUniqueEmail(UserJoinRequestDto requestDto) {
        if(userRepository.findByEmailValue(requestDto.getEmail()).isPresent()) {
            throw new DuplicateEmailException();
        }
    }

    private void checkCorrectEmailCheckCode(UserJoinRequestDto requestDto) {
        if(emailService.verityCode(requestDto.getCheckCode())) {
            throw new InvalidAuthCodeException();
        }
    }

    private void checkUniqueNickname(UserJoinRequestDto requestDto) {
        boolean isDuplicateNickname = userRepository.existsUserByNicknameValue(requestDto.getNickname());
        if(isDuplicateNickname) {
            throw new DuplicateNicknameException();
        }
    }

    public User getUserByEmail(UserLoginRequestDto requestDto) {
        return userRepository.findByEmailValue(requestDto.getEmail())
                .orElseThrow(InvalidEmailException::new);
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
}
