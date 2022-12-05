package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.dto.UsersRankResponseDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {
    private static final String KEY = "ranking";
    private final RedisTemplate redisTemplate;

    public void updateUser(User user) {
        ZSetOperations<String, User> ranking = redisTemplate.opsForZSet();
        ranking.add(KEY, user, user.getContributionLevel());
    }

    @Transactional
    public List<UsersRankResponseDto> getRankingList() {
        ZSetOperations<String, User> ranking = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<User>> users = ranking.reverseRangeWithScores(KEY, 0, 10);
        return users.stream()
                .map(UsersRankResponseDto::new)
                .collect(Collectors.toList());
    }
}
