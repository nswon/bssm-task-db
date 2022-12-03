package taskdb.taskdb.domain.answer.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.AnswerRepository;
import taskdb.taskdb.domain.answer.exception.AnswerNotFoundException;

@Component
@RequiredArgsConstructor
public class AnswerFacade {
    private final AnswerRepository answerRepository;

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(AnswerNotFoundException::new);
    }
}
