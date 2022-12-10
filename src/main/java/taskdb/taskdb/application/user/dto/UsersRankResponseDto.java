package taskdb.taskdb.application.user.dto;

import lombok.Getter;
import taskdb.taskdb.domain.user.entity.User;

@Getter
public class UsersRankResponseDto {
    private final int rank;
    private final Long id;
    private final String imgUrl;
    private final String nickname;
    private final int contributionLevel;
    private final int answerCount;
    private final int questionCount;

    public UsersRankResponseDto(int rank, User user) {
        this.rank = rank;
        this.id = user.getId();
        this.imgUrl = user.getImgUrl();
        this.nickname = user.getNickname();
        this.contributionLevel = user.getContributionLevel();
        this.answerCount = user.getAnswerCount();
        this.questionCount = user.getQuestionCount();
    }
}
