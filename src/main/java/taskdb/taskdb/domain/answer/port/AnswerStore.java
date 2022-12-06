package taskdb.taskdb.domain.answer.port;

import taskdb.taskdb.domain.answer.domain.Answer;

public interface AnswerStore {
    Answer store(Answer answer);
    void delete(Answer answer);
}
