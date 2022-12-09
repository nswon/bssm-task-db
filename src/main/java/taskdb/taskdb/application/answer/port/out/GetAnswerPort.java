package taskdb.taskdb.application.answer.port.out;

import taskdb.taskdb.domain.answer.domain.Answer;

import java.util.List;

public interface GetAnswerPort {
    Answer getAnswer(Long id);
    List<Answer> getAnswers();
}
