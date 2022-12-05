package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;
import taskdb.taskdb.domain.user.domain.User;

@Getter
public class UsersRankResponseDto {
    private double count;
    private String image;
    private String nickname;
    private int contributionLevel;
    private int answerCount;
    private int questionCount;

    public UsersRankResponseDto() {
    }

    public UsersRankResponseDto(ZSetOperations.TypedTuple<User> userTypedTuple) {
        this.count = userTypedTuple.getScore();
        this.image = userTypedTuple.getValue().getImage().getUrl();
        this.nickname = userTypedTuple.getValue().getNickname().getValue();
        this.contributionLevel = userTypedTuple.getValue().getContributionLevel();
        this.answerCount = userTypedTuple.getValue().getAnswerCount();
        this.questionCount = userTypedTuple.getValue().getQuestionCount();
    }
}
