package taskdb.taskdb.domain.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitQuestionService {
    private static final String KEY = "visit";
    private final StringRedisTemplate visitQuestionIds;

    public void addQuestionId(String questionId) {
        ListOperations<String, String> alreadyVisitQuestionIds = visitQuestionIds.opsForList();
        alreadyVisitQuestionIds.rightPush(KEY, questionId);
    }

    public List<String> getQuestionIds() {
        ListOperations<String, String> alreadyVisitQuestionIds = visitQuestionIds.opsForList();
        int questionIdsSize = getQuestionIdsSize();
        return alreadyVisitQuestionIds.range(KEY, 0, questionIdsSize);
    }

    @Scheduled(cron = "0 0 0 * * *")
    void removeQuestionIds() {
        ListOperations<String, String> alreadyVisitQuestionIds = visitQuestionIds.opsForList();
        int questionIdsSize = getQuestionIdsSize();
        for(int i=0; i<questionIdsSize; i++) {
            alreadyVisitQuestionIds.leftPop(KEY);
        }
    }

    private int getQuestionIdsSize() {
        ListOperations<String, String> alreadyVisitQuestionIds = visitQuestionIds.opsForList();
        return Math.toIntExact(alreadyVisitQuestionIds.size(KEY));
    }
}
