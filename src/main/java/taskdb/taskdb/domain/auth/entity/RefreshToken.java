package taskdb.taskdb.domain.auth.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refresh", timeToLive = 2592000)
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
