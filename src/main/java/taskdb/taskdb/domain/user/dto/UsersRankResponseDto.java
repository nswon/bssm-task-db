package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import taskdb.taskdb.domain.user.domain.User;

@Getter
public class UsersRankResponseDto {
    private final int count;
    private final String image;
    private final String nickname;
    private final int contributionLevel;
    private final int answerCount;
    private final int questionCount;

    public UsersRankResponseDto(int count, User user) {
        this.count = count;
        this.image = user.getImage().getUrl();
        this.nickname = user.getNickname().getValue();
        this.contributionLevel = user.getContributionLevel();
        this.answerCount = user.getAnswerCount();
        this.questionCount = user.getQuestionCount();
    }
}
