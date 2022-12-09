package taskdb.taskdb.adapter.question.out.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.question.port.out.GetVisitQuestionPort;
import taskdb.taskdb.application.question.port.out.SaveVisitQuestionPort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VisitQuestionAdapter implements SaveVisitQuestionPort, GetVisitQuestionPort {
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
