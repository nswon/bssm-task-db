package taskdb.taskdb.infrastructure.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.exception.AnswerNotFoundException;
import taskdb.taskdb.domain.answer.port.AnswerReader;
import taskdb.taskdb.domain.answer.repository.AnswerRepository;

@Component
@RequiredArgsConstructor
public class AnswerReaderImpl implements AnswerReader {
    private final AnswerRepository answerRepository;

    @Override
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(AnswerNotFoundException::new);
    }
}
