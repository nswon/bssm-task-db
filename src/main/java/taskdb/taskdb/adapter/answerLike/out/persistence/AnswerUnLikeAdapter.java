package taskdb.taskdb.adapter.answerLike.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.answerLike.port.out.DeleteAnswerUnLikePort;
import taskdb.taskdb.application.answerLike.port.out.ExistAnswerUnLikePort;
import taskdb.taskdb.application.answerLike.port.out.GetAnswerUnLikePort;
import taskdb.taskdb.application.answerLike.port.out.SaveAnswerUnLikePort;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.answerLike.entity.AnswerUnLike;
import taskdb.taskdb.domain.user.entity.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerUnLikeAdapter implements
        ExistAnswerUnLikePort, SaveAnswerUnLikePort, DeleteAnswerUnLikePort, GetAnswerUnLikePort {
    private final AnswerUnLikeRepository answerUnLikeRepository;

    @Override
    public boolean hasAnswerUnLike(Answer answer, User user) {
        return answerUnLikeRepository.existsByAnswerAndUser(answer, user);
    }

    @Override
    public AnswerUnLike save(AnswerUnLike answerUnLike) {
        return answerUnLikeRepository.save(answerUnLike);
    }

    @Override
    public void delete(Answer answer, User user) {
        answerUnLikeRepository.deleteByAnswerAndUser(answer, user);
    }

    @Override
    public List<AnswerUnLike> getAnswerUnLikes(Answer answer) {
        return answerUnLikeRepository.findByAnswer(answer);
    }
}
