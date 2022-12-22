package taskdb.taskdb.application.question.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.application.answer.dto.AnswersResponseDto;
import taskdb.taskdb.domain.question.entity.Content;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.question.entity.Title;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    public Question of(QuestionCreateRequestDto requestDto, User user) {
        Question question = Question.builder()
                .title(Title.of(requestDto.getTitle()))
                .content(Content.of(requestDto.getContent()))
                .category(requestDto.getCategory())
                .build();
        question.confirmUser(user);
        return question;
    }

    public List<QuestionAllResponseDto> of(List<Question> questions) {
        return questions.stream()
                .map(QuestionAllResponseDto::new)
                .collect(Collectors.toList());
    }

    public QuestionDetailResponse of(boolean hasLike,
                                     boolean hasUnLike,
                                     Question question,
                                     List<AnswersResponseDto> answersResponseDto) {
        return new QuestionDetailResponse(hasLike, hasUnLike, question, answersResponseDto);
    }
}
