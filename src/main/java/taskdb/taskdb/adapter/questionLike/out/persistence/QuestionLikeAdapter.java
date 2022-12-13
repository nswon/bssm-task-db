package taskdb.taskdb.adapter.questionLike.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.questionLike.port.out.DeleteQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.GetQuestionLikePort;
import taskdb.taskdb.application.questionLike.port.out.SaveQuestionLikePort;
import taskdb.taskdb.domain.questionLike.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionLikeAdapter implements
        ExistQuestionLikePort, DeleteQuestionLikePort, SaveQuestionLikePort, GetQuestionLikePort {
    private final QuestionLikeRepository questionLikeRepository;

    @Override
    public boolean hasQuestionLike(Question question, User user) {
        return questionLikeRepository.existsByQuestionAndUser(question, user);
    }

    @Override
    public void delete(Question question, User user) {
        questionLikeRepository.deleteByQuestionAndUser(question, user);
    }

    @Override
    public void save(QuestionLike questionLike) {
        questionLikeRepository.save(questionLike);
    }

    @Override
    public List<QuestionLike> getQuestionLike(Question question) {
        return questionLikeRepository.findByQuestion(question);
    }
}
