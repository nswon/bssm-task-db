package taskdb.taskdb.domain.user.presentation.dto.response;

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
        this.image = user.getImgUrl();
        this.nickname = user.getNickname();
        this.contributionLevel = user.getContributionLevel();
        this.answerCount = user.getAnswerCount();
        this.questionCount = user.getQuestionCount();
    }
}
