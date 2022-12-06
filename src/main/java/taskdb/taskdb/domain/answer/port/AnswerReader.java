package taskdb.taskdb.domain.answer.port;

import taskdb.taskdb.domain.answer.domain.Answer;

public interface AnswerReader {
    Answer getAnswerById(Long id);
}
