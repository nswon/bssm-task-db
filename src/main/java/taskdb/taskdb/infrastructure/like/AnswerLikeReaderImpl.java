package taskdb.taskdb.infrastructure.like;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.answer.domain.Answer;
import taskdb.taskdb.domain.like.domain.AnswerLike;
import taskdb.taskdb.domain.like.port.AnswerLikeReader;
import taskdb.taskdb.domain.like.repository.AnswerLikeRepository;
import taskdb.taskdb.domain.user.domain.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerLikeReaderImpl implements AnswerLikeReader {
    private final AnswerLikeRepository answerLikeRepository;

    @Override
    public boolean hasByAnswerAndUser(Answer answer, User user) {
        return answerLikeRepository.existsByAnswerAndUser(answer, user);
    }

    @Override
    public List<AnswerLike> getAnswerLikesByAnswer(Answer answer) {
        return answerLikeRepository.findByAnswer(answer);
    }
}
