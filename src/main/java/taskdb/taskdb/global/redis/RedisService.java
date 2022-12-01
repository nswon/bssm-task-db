package taskdb.taskdb.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> visitQuestions;

    public void addVisitQuestion(String value) {
        SetOperations<String, String> valueOperations = visitQuestions.opsForSet();
        valueOperations.add("visit", value);
    }
}
