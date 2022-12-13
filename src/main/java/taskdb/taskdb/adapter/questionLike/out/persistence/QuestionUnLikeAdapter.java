package taskdb.taskdb.adapter.questionLike.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.questionLike.port.out.DeleteQuestionUnLikePort;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionUnLikePort;
import taskdb.taskdb.application.questionLike.port.out.GetQuestionUnLikePort;
import taskdb.taskdb.application.questionLike.port.out.SaveQuestionUnLikePort;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.questionLike.entity.QuestionUnLike;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionUnLikeAdapter implements
        ExistQuestionUnLikePort, SaveQuestionUnLikePort, DeleteQuestionUnLikePort, GetQuestionUnLikePort {
    private final QuestionUnLikeRepository questionUnLikeRepository;

    @Override
    public boolean hasQuestionUnLike(Question question, User user) {
        return questionUnLikeRepository.existsByQuestionAndUser(question, user);
    }

    @Override
    public QuestionUnLike save(QuestionUnLike questionUnLike) {
        return questionUnLikeRepository.save(questionUnLike);
    }

    @Override
    public void delete(Question question, User user) {
        questionUnLikeRepository.deleteByQuestionAndUser(question, user);
    }

    @Override
    public List<QuestionUnLike> getQuestionUnLikes(Question question) {
        return questionUnLikeRepository.findByQuestion(question);
    }
}
