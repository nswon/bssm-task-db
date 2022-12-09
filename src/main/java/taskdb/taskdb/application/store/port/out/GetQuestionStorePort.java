package taskdb.taskdb.application.store.port.out;

import taskdb.taskdb.domain.store.entity.QuestionStore;

import java.util.List;

public interface GetQuestionStorePort {
    List<QuestionStore> getQuestions();
    QuestionStore getQuestionStore(Long id);
}
