package taskdb.taskdb.adapter.question.out.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.question.port.out.GetVisitQuestionPort;
import taskdb.taskdb.application.question.port.out.SaveVisitQuestionPort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public boolean hasQuestionId(Long id) {
        List<String> ids = visitQuestionRepository.existsQuestionId(id)
                .orElse(Collections.emptyList());
        return ids.contains(String.valueOf(id));
    }
}
