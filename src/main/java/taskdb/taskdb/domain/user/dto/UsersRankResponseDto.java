package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import taskdb.taskdb.domain.user.domain.User;

@Getter
public class UsersRankResponseDto {
    private int count;
    private String image;
    private String nickname;
    private int contributionLevel;
    private int answerCount;
    private int questionCount;

    public UsersRankResponseDto() {
    }

    public UsersRankResponseDto(int count, User user) {
        this.count = count;
        this.image = user.getImage().getUrl();
        this.nickname = user.getNickname().getValue();
        this.contributionLevel = user.getContributionLevel();
        this.answerCount = user.getAnswerCount();
        this.questionCount = user.getQuestionCount();
    }
}
