package taskdb.taskdb.application.store.port.out;

import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

public interface SaveQuestionStorePort {
    QuestionStore save(User user, QuestionStore questionStore);
}
