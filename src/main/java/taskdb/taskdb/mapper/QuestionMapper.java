package taskdb.taskdb.mapper;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.domain.Content;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.question.domain.Title;
import taskdb.taskdb.domain.question.dto.QuestionCreateRequestDto;
import taskdb.taskdb.domain.question.dto.QuestionAllElementResponse;
import taskdb.taskdb.domain.question.dto.QuestionAllResponseDto;
import taskdb.taskdb.domain.question.dto.QuestionDetailResponse;
import taskdb.taskdb.domain.user.domain.User;

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
