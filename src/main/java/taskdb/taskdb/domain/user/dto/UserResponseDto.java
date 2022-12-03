package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import taskdb.taskdb.domain.question.dto.QuestionsResponseDto;
import taskdb.taskdb.domain.store.dto.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

@Getter
public class UserResponseDto {
    private String image;
    private String nickname;
    private int contributionLevel;
    private List<QuestionsResponseDto> getMyQuestions;
    private List<StoreQuestionsResponseDto> getSavedQuestions;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.image = user.getImage().getUrl();
        this.nickname = user.getNickname().getValue();
        this.contributionLevel = user.getContributionLevel();
        this.getMyQuestions = user.toQuestionsResponseDto();
        this.getSavedQuestions = user.toStoreQuestionsResponseDto();
    }
}
