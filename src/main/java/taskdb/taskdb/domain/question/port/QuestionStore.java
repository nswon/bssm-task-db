package taskdb.taskdb.domain.question.port;

import taskdb.taskdb.domain.question.domain.Question;

public interface QuestionStore {
    Question store(Question question);
    void delete(Question question);
}
