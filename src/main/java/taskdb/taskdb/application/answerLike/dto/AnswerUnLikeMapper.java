package taskdb.taskdb.application.answerLike.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class AnswerUnLikeMapper {
    public AnswerUnLike toEntity(Answer answer, User user) {
        AnswerUnLike answerUnLike = AnswerUnLike.builder()
                .answer(answer)
                .user(user)
                .build();
        answerUnLike.addAnswer();
        answerUnLike.addUser();
        return answerUnLike;
    }
}
