package taskdb.taskdb.application.store.port.out;

import taskdb.taskdb.domain.store.entity.QuestionStore;

public interface SaveQuestionStorePort {
    QuestionStore save(QuestionStore questionStore);
}
