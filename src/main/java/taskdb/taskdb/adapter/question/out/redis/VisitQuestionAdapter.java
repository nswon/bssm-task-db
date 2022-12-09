package taskdb.taskdb.adapter.question.out.redis;

import lombok.RequiredArgsConstructor;
import taskdb.taskdb.application.question.port.out.GetVisitQuestionUseCase;
import taskdb.taskdb.application.question.port.out.SaveVisitQuestionUseCase;

import java.util.List;

@RequiredArgsConstructor
public class VisitQuestionAdapter implements SaveVisitQuestionUseCase, GetVisitQuestionUseCase {
    private final VisitQuestionRepository visitQuestionRepository;

    @Override
    public Long save(Long id) {
        visitQuestionRepository.save(id);
        return id;
    }

    @Override
    public List<String> getVisitQuestionIds() {
        return visitQuestionRepository.getQuestionIds();
    }
}
