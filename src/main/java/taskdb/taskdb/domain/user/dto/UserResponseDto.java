package taskdb.taskdb.domain.user.dto;

import lombok.Getter;
import taskdb.taskdb.domain.question.dto.QuestionAllElementResponse;
import taskdb.taskdb.domain.store.dto.QuestionStoreResponseDto;
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
    private List<QuestionAllElementResponse> getMyQuestions;
    private List<QuestionStoreResponseDto> getSavedQuestions;

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

    private List<QuestionAllElementResponse> toQuestionsResponse(User user) {
        return user.getQuestions().stream()
                .map(QuestionAllElementResponse::new)
                .collect(Collectors.toList());
    }

    private List<QuestionStoreResponseDto> toStoreQuestionsResponse(User user) {
        return user.getQuestionStores().stream()
                .map(QuestionStoreResponseDto::new)
                .collect(Collectors.toList());
    }
}
