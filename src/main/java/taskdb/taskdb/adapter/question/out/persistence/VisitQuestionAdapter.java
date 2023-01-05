package taskdb.taskdb.adapter.question.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.question.port.out.GetVisitQuestionPort;
import taskdb.taskdb.application.question.port.out.SaveVisitQuestionPort;
import taskdb.taskdb.domain.question.entity.VisitQuestion;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VisitQuestionAdapter implements SaveVisitQuestionPort, GetVisitQuestionPort {
    private static final String KEY = "visit";
    private final VisitQuestionRepository visitQuestionRepository;

    @Override
    public boolean hasQuestionId(UUID id) {
        return visitQuestionRepository.existsByQuestionId(id);
    }

    @Transactional
    @Override
    public void save(UUID id) {
        VisitQuestion visitQuestion = VisitQuestion.of(KEY, id);
        visitQuestionRepository.save(visitQuestion);
    }
}