package taskdb.taskdb.adapter.question.out.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitQuestion implements VisitQuestionRepository {
    private static final String KEY = "visit";
    private static final String EVERYDAY_CRON = "0 0 0 * * *";
    private final StringRedisTemplate visitQuestionIds;

    @Override
    public void save(Long questionId) {
        ListOperations<String, String> alreadyVisitQuestionIds = visitQuestionIds.opsForList();
        alreadyVisitQuestionIds.rightPush(KEY, String.valueOf(questionId));
    }

    @Override
    public Optional<List<String>> existsQuestionId(Long id) {
        ListOperations<String, String> alreadyVisitQuestionIds = visitQuestionIds.opsForList();
        int questionIdsSize = getQuestionIdsSize();
        return Optional.ofNullable(
                alreadyVisitQuestionIds.range(KEY, 0, questionIdsSize)
        );
    }

    @Scheduled(cron = EVERYDAY_CRON)
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
