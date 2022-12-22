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
import taskdb.taskdb.infrastructure.security.jwt.SecurityUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPort, GetUserPort {
    private final UserRepository userRepository;
    private final UserQuerydslRepository userQuerydslRepository;
    private static final int RANK_SIZE = 10;

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
        return userQuerydslRepository.findById(id)
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

    @Override
    public List<User> getUser() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparing(User::getContributionLevel).reversed()
                        .thenComparing(User::getAnswerCount).reversed()
                        .thenComparing(User::getNickname))
                .limit(RANK_SIZE)
                .collect(Collectors.toList());
    }
}
