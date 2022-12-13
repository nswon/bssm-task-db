package taskdb.taskdb.application.questionLike.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.questionLike.entity.QuestionUnLike;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class QuestionUnLikeMapper {
    public QuestionUnLike toEntity(Question question, User user) {
        QuestionUnLike questionUnLike = QuestionUnLike.builder()
                .question(question)
                .user(user)
                .build();
        questionUnLike.addQuestion();
        questionUnLike.addUser();
        return questionUnLike;
    }
}
