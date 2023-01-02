package taskdb.taskdb.adapter.answerLike.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.answerLike.port.out.DeleteAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.GetAnswerLikePort;
import taskdb.taskdb.application.answerLike.port.out.SaveAnswerLikePort;
import taskdb.taskdb.domain.answer.entity.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerLike;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerLikeAdapter implements
        ExistAnswerLikePort, DeleteAnswerLikePort, SaveAnswerLikePort, GetAnswerLikePort {
    private final AnswerLikeRepository answerLikeRepository;

    @Override
    public AnswerLike save(AnswerLike answerLike) {
        answerLikeRepository.save(answerLike);
        return answerLike;
    }

    @Override
    public void delete(Answer answer, User user) {
        answerLikeRepository.deleteByAnswerAndUser(answer, user);
    }

    @Override
    public boolean hasAnswerLike(Answer answer, User user) {
        return answerLikeRepository.existsByAnswerAndUser(answer, user);
    }

    @Override
    public List<AnswerLike> getAnswerLike(Answer answer) {
        return answerLikeRepository.findByAnswer(answer);
    }
}
