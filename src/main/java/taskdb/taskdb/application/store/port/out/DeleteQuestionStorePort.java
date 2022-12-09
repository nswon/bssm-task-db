package taskdb.taskdb.application.store.port.out;

import taskdb.taskdb.domain.store.entity.QuestionStore;

public interface DeleteQuestionStorePort {
    void delete(QuestionStore questionStore);
    void delete();
}
