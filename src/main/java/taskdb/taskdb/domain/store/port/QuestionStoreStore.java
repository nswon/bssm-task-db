package taskdb.taskdb.domain.store.port;

import taskdb.taskdb.domain.store.domain.QuestionStore;

public interface QuestionStoreStore {
    QuestionStore store(QuestionStore questionStore);
    QuestionStore delete(QuestionStore questionStore);
    void deleteAll();
}