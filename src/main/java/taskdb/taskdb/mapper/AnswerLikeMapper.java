package taskdb.taskdb.mapper;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.user.domain.User;

@Component
public class AnswerLikeMapper {
    public AnswerLike of(Answer answer, User user) {
        return AnswerLike.builder()
                .answer(answer)
                .user(user)
                .build();
    }
}
