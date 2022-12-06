package taskdb.taskdb.infrastructure.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.port.QuestionStore;
import taskdb.taskdb.domain.question.repository.QuestionRepository;

@Component
@RequiredArgsConstructor
public class QuestionStoreImpl implements QuestionStore {
    private final QuestionRepository questionRepository;

    @Override
    public Question store(Question question) {
        questionRepository.save(question);
        return question;
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }
}
