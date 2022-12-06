package taskdb.taskdb.infrastructure.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.port.AnswerStore;
import taskdb.taskdb.domain.answer.repository.AnswerRepository;

@Component
@RequiredArgsConstructor
public class AnswerStoreImpl implements AnswerStore {
    private final AnswerRepository answerRepository;

    @Override
    public Answer store(Answer answer) {
        answerRepository.save(answer);
        return answer;
    }

    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
