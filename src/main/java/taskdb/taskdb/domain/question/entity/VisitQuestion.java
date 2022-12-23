package taskdb.taskdb.domain.question.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "visit", timeToLive = 86400)
public class VisitQuestion {
    @Id
    private String key;
    private Long questionId;

    private VisitQuestion() {
    }

    private VisitQuestion(String key, Long questionId) {
        this.key = key;
        this.questionId = questionId;
    }

    public static VisitQuestion of(String key, Long questionId) {
        return new VisitQuestion(key, questionId);
    }
}
