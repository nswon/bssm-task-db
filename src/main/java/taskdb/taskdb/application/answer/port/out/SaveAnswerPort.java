package taskdb.taskdb.application.answer.port.out;

import taskdb.taskdb.domain.answer.entity.Answer;

public interface SaveAnswerPort {
    Answer save(Answer answer);
}
