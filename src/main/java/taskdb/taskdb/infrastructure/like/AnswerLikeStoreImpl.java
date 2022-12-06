package taskdb.taskdb.infrastructure.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.like.port.AnswerLikeStore;
import taskdb.taskdb.domain.like.repository.AnswerLikeRepository;
import taskdb.taskdb.domain.user.domain.User;

@Component
@RequiredArgsConstructor
public class AnswerLikeStoreImpl implements AnswerLikeStore {
    private final AnswerLikeRepository answerLikeRepository;

    @Override
    public AnswerLike store(AnswerLike answerLike) {
        answerLikeRepository.save(answerLike);
        return answerLike;
    }

    @Override
    public void delete(Answer answer, User user) {
        answerLikeRepository.deleteByAnswerAndUser(answer, user);
    }
}
