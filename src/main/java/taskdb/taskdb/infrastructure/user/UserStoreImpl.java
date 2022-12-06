package taskdb.taskdb.infrastructure.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.exception.DuplicateEmailException;
import taskdb.taskdb.domain.user.exception.DuplicateNicknameException;
import taskdb.taskdb.domain.user.port.UserStore;
import taskdb.taskdb.domain.user.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserStoreImpl implements UserStore {
    private final UserRepository userRepository;

    @Override
    public User store(User user) {
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
}
