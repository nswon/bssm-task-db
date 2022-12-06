package taskdb.taskdb.domain.user.facade;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.exception.DifferentUserException;

@Component
public class UserFacade {
    public void checkDifferentUser(User user, User writer) {
        if(user.isNotCorrectEmail(writer.getEmail())) {
            throw new DifferentUserException();
        }
    }
}
