package taskdb.taskdb.application.answer.port.out;

import taskdb.taskdb.domain.answer.entity.Answer;

public interface DeleteAnswerPort {
    Answer delete(Answer answer);
}
