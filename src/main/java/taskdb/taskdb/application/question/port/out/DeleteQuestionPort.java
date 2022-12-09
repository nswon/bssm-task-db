package taskdb.taskdb.application.question.port.out;

import taskdb.taskdb.domain.question.entity.Question;

public interface DeleteQuestionPort {
    void delete(Question question);
}
