package taskdb.taskdb.application.question.port.out;

import taskdb.taskdb.domain.question.entity.Question;

public interface SaveQuestionPort {
    Question save(Question question);
}
