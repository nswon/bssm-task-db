package taskdb.taskdb.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.auth.exception.InvalidEmailException;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.exception.AuthorizationException;
import taskdb.taskdb.domain.user.exception.UserNotFoundException;
import taskdb.taskdb.domain.user.port.UserReader;
import taskdb.taskdb.domain.user.repository.UserRepository;
import taskdb.taskdb.global.security.jwt.SecurityUtil;

@Component
@RequiredArgsConstructor
public class UserReaderImpl implements UserReader {
    private final UserRepository userRepository;

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
