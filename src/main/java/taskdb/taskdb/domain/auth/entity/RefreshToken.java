package taskdb.taskdb.domain.auth.entity;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "refresh", timeToLive = 30 * 24 * 60 * 60 * 1000L)
public class RefreshToken {
    @Id
    private String email;
    private String token;

    protected RefreshToken() {
    }

    private RefreshToken(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public static RefreshToken of(String email, String token) {
        return new RefreshToken(email, token);
    }
}
