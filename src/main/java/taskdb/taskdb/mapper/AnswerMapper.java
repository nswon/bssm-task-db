package taskdb.taskdb.mapper;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answer.domain.Content;
import taskdb.taskdb.domain.answer.dto.AnswerCreateRequestDto;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

@Component
public class AnswerMapper {
    public Answer of(AnswerCreateRequestDto requestDto, User user, Question question) {
        return Answer.builder()
                .content(Content.of(requestDto.getContent()))
                .user(user)
                .question(question)
                .build();
    }
}
