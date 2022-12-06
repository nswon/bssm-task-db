package taskdb.taskdb.infrastructure.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.like.domain.QuestionLike;
import taskdb.taskdb.domain.like.port.QuestionLikeStore;
import taskdb.taskdb.domain.like.repository.QuestionLikeRepository;
import taskdb.taskdb.domain.question.domain.Question;
import taskdb.taskdb.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class QuestionLikeStoreImpl implements QuestionLikeStore {
    private final QuestionLikeRepository questionLikeRepository;

    @Override
    public QuestionLike store(QuestionLike questionLike) {
        questionLikeRepository.save(questionLike);
        return questionLike;
    }

    @Override
    public void delete(Question question, User user) {
        questionLikeRepository.deleteByQuestionAndUser(question, user);
    }
}
