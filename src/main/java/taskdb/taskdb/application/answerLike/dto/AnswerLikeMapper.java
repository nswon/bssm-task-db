package taskdb.taskdb.application.answerLike.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerLike;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class AnswerLikeMapper {
    public AnswerLike of(Answer answer, User user) {
        return AnswerLike.builder()
                .answer(answer)
                .user(user)
                .build();
    }
}
