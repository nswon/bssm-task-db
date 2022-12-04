package taskdb.taskdb.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.domain.user.domain.User;
import taskdb.taskdb.domain.user.dto.UsersRankResponseDto;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class RankingService {
    private static final String KEY = "ranking";
    private static final int RANK_SIZE = 10;
    private final RedisTemplate redisTemplate;

    public void updateUser(User user) {
        SetOperations<String, User> rankUsers = redisTemplate.opsForSet();
        rankUsers.add(KEY, user);
    }

    @Transactional
    public List<UsersRankResponseDto> getRankingList() {
        SetOperations<String, User> listOperations = redisTemplate.opsForSet();
        Set<User> users = listOperations.members(KEY);
        List<User> rankUsers = getRankingUsers(users);
        return IntStream.range(0, rankUsers.size())
                .boxed()
                .map(count -> new UsersRankResponseDto(count + 1, rankUsers.get(count)))
                .collect(Collectors.toList());
    }

    private List<User> getRankingUsers(Set<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getContributionLevel).reversed()
                        .thenComparing(User::getAnswerCount).reversed()
                        .thenComparing(User::getQuestionCount).reversed())
                .limit(RANK_SIZE)
                .collect(Collectors.toList());
    }
}
