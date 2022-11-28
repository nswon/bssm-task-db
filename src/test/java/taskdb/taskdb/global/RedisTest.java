package taskdb.taskdb.global;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void should_Expire_When_redis() throws InterruptedException {
        final String key = "a";
        final String value = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
        final Boolean expire = redisTemplate.expire(key, 5, TimeUnit.SECONDS);
        Thread.sleep(6000);
        final String s = valueOperations.get(key);
        assertThat(expire).isTrue();
        assertThat(s).isNull();
    }
}
