package taskdb.taskdb.application.user.policy;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

@Component
public class DuplicateUserPolicy implements UserPolicy {

    @Override
    public void check(User user, User writer) {
        boolean isSameUser = user.equals(writer);
        if(!isSameUser) {
            throw new DifferentUserException();
        }
    }
}
