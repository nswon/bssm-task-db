package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import taskdb.taskdb.domain.question.dto.QuestionsResponseDto;
import taskdb.taskdb.domain.store.dto.StoreQuestionsResponseDto;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseDto {
    private String image;
    private String nickname;
    private String bio;
    private int grade;
    private int contributionLevel;
    private List<QuestionsResponseDto> getMyQuestions;
    private List<StoreQuestionsResponseDto> getSavedQuestions;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.image = user.getImgUrl();
        this.nickname = user.getNickname();
        this.bio = user.getBio();
        this.grade = user.getGrade();
        this.contributionLevel = user.getContributionLevel();
        this.getMyQuestions = toQuestionsResponse(user);
        this.getSavedQuestions = toStoreQuestionsResponse(user);
    }

    private List<QuestionsResponseDto> toQuestionsResponse(User user) {
        return user.getQuestions().stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }

    private List<StoreQuestionsResponseDto> toStoreQuestionsResponse(User user) {
        return user.getQuestionStores().stream()
                .map(StoreQuestionsResponseDto::new)
                .collect(Collectors.toList());
    }
}
