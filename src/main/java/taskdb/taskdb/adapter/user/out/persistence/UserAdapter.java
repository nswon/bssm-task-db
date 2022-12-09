package taskdb.taskdb.adapter.user.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.exception.AuthorizationException;
import taskdb.taskdb.domain.user.exception.UserNotFoundException;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.domain.auth.exception.InvalidEmailException;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.domain.user.exception.DuplicateEmailException;
import taskdb.taskdb.domain.user.exception.DuplicateNicknameException;
import taskdb.taskdb.application.user.port.out.SaveUserPort;
import taskdb.taskdb.global.security.jwt.SecurityUtil;

@Component
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPort, GetUserPort {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        validate(user);
        return userRepository.save(user);
    }

    private void validate(User user) {
        checkUniqueEmail(user);
        checkUniqueNickname(user);
    }

    private void checkUniqueEmail(User user) {
        if(userRepository.findByEmailValue(user.getEmail()).isPresent()) {
            throw new DuplicateEmailException();
        }
    }

    private void checkUniqueNickname(User user) {
        boolean isDuplicateNickname = userRepository.existsUserByNicknameValue(user.getNickname());
        if(isDuplicateNickname) {
            throw new DuplicateNicknameException();
        }
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmailValue(SecurityUtil.getLoginUserEmail())
                .orElseThrow(AuthorizationException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailValue(email)
                .orElseThrow(InvalidEmailException::new);
    }
}
