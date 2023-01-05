package taskdb.taskdb.domain.question.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Getter
@RedisHash(value = "visit", timeToLive = 86400)
public class VisitQuestion {
    @Id
    private String key;
    private UUID questionId;

    private VisitQuestion() {
    }

    private VisitQuestion(String key, UUID questionId) {
        this.key = key;
        this.questionId = questionId;
    }

    public static VisitQuestion of(String key, UUID questionId) {
        return new VisitQuestion(key, questionId);
    }
}
