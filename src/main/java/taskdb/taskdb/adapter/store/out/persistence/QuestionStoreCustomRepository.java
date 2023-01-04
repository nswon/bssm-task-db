package taskdb.taskdb.adapter.store.out.persistence;

import taskdb.taskdb.domain.store.entity.QuestionStore;
import taskdb.taskdb.domain.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface QuestionStoreCustomRepository {
    Optional<QuestionStore> getQuestionStoreByUser(User user, UUID id);
}
