package taskdb.taskdb.adapter.like.out.persistence;

import lombok.RequiredArgsConstructor;
import taskdb.taskdb.application.like.port.out.DeleteQuestionLikePort;
import taskdb.taskdb.application.like.port.out.ExistQuestionLikePort;
import taskdb.taskdb.application.like.port.out.GetQuestionLikePort;
import taskdb.taskdb.application.like.port.out.SaveQuestionLikePort;
import taskdb.taskdb.domain.like.entity.QuestionLike;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

@RequiredArgsConstructor
public class QuestionLikeAdapter implements
        ExistQuestionLikePort, DeleteQuestionLikePort, SaveQuestionLikePort, GetQuestionLikePort {
    private final QuestionLikeRepository questionLikeRepository;

    @Override
    public boolean hasAnswerLike(Question question, User user) {
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
