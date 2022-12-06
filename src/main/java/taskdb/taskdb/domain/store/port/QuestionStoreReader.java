package taskdb.taskdb.domain.store.port;

import taskdb.taskdb.domain.store.domain.QuestionStore;

import java.util.List;

public interface QuestionStoreReader {
    List<QuestionStore> getQuestions();
    QuestionStore getQuestionStore(Long id);
}
