package taskdb.taskdb.application.answer.port.out;

import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;
import java.util.UUID;

public interface GetAnswerPort {
    Answer getAnswer(UUID id);
    List<Answer> getAnswers();
    List<Answer> getAnswers(Question question);
}
