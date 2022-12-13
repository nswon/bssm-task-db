package taskdb.taskdb.application.questionLike.dto;

import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.questionLike.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

@Component
public class QuestionLikeMapper {
    public QuestionLike toEntity(Question question, User user) {
        QuestionLike questionLike = QuestionLike.builder()
                .question(question)
                .user(user)
                .build();
        questionLike.addQuestion();
        questionLike.addUser();
        return questionLike;
    }
}
