package taskdb.taskdb.adapter.answer.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.answer.port.out.DeleteAnswerPort;
import taskdb.taskdb.application.answer.port.out.GetAnswerPort;
import taskdb.taskdb.application.answer.port.out.SaveAnswerPort;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.exception.AnswerNotFoundException;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerAdapter implements SaveAnswerPort, GetAnswerPort, DeleteAnswerPort {
    private final AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer getAnswer(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(AnswerNotFoundException::new);
    }

    @Override
    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public List<Answer> getAnswers(Question question) {
        return answerRepository.findByQuestion(question);
    }

    @Override
    public Answer delete(Answer answer) {
        answerRepository.delete(answer);
        return answer;
    }
}
