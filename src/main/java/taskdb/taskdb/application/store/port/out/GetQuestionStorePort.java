package taskdb.taskdb.application.store.port.out;

import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface GetQuestionStorePort {
    List<QuestionStore> getQuestions(User user);
    QuestionStore getQuestionStore(User user, UUID id);
}
