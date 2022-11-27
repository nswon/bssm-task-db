package taskdb.taskdb.domain.user.presentation.dto.user.response;

import lombok.Getter;
import taskdb.taskdb.domain.user.domain.User;

@Getter
public class UsersRankResponseDto {
    private final String nickname;
    private final int contributionLevel;
    private final int answerCount;
    private final int questionCount;

    public UsersRankResponseDto(User user) {
        this.nickname = user.getNickname();
        this.contributionLevel = user.getContributionLevel();
        this.answerCount = user.getAnswerCount();
        this.questionCount = user.getQuestionCount();
    }
}