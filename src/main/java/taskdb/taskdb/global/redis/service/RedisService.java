package taskdb.taskdb.global.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private static final String RECOMMEND_KEYWORD_NAME = "Recommend";
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisTemplate<String, Object> recommendRedisTemplate;

    public String getAlreadyVisitedQuestions(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setVisitQuestion(String key, String value, int time) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public void setRecommendKeyword(String value) {
        SetOperations<String, Object> setOperations = recommendRedisTemplate.opsForSet();
        setOperations.add(RECOMMEND_KEYWORD_NAME, value);
    }

    public Set<Object> getRecommendKeywords() {
        SetOperations<String, Object> setOperations = recommendRedisTemplate.opsForSet();
        return setOperations.members(RECOMMEND_KEYWORD_NAME);
    }

    public void deleteRecommendKeywords() {
        recommendRedisTemplate.delete(RECOMMEND_KEYWORD_NAME);
    }
}
