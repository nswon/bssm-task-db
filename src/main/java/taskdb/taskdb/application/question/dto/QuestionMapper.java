package taskdb.taskdb.application.question.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.entity.Content;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.application.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.application.question.dto.QuestionAllElementResponse;
import taskdb.taskdb.application.question.dto.QuestionAllResponseDto;
import taskdb.taskdb.application.question.dto.QuestionDetailResponse;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    public Question of(QuestionCreateRequestDto requestDto, User user) {
        return Question.builder()
                .title(Title.of(requestDto.getTitle()))
                .content(Content.of(requestDto.getContent()))
                .category(requestDto.getCategory())
                .user(user)
                .build();
    }

    public QuestionAllResponseDto of(List<Question> questions) {
        List<QuestionAllElementResponse> getQuestionAllElements = getQuestionsElements(questions);
        return new QuestionAllResponseDto(getQuestionAllElements);
    }

    public QuestionDetailResponse of(Question question) {
        return new QuestionDetailResponse(question);
    }

    private List<QuestionAllElementResponse> getQuestionsElements(List<Question> questions) {
        return questions.stream()
                .map(QuestionAllElementResponse::new)
                .collect(Collectors.toList());
    }
}
